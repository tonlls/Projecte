using DBBasic;
using DBBasic.Model;
using DBBasic.output_obj;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Server
{
    public partial class MainWindow : Window
    {
        bool started = false;
        DBInterface db;
        private Socket sock;
        private bool stoping = false;
        private int clients = 0;
        private List<StopableThread> simulations = new List<StopableThread>();

        public MainWindow()
        {
            InitializeComponent();
            //var y=typeof(MySqlDriver.MySql).AssemblyQualifiedName;
            db = DBFactory.getInstance("MySqlDriver.MySql, MySqlDriver");
            //var x=db.getInfo();
        }
        private void Start()
        {
            if (!started)
            {
                started = true;
                log("Server starting...");
                Task.Run(() =>
                {
                    start_simulation();
                    start_server();
                });
                log("Server started...");
            }
        }

        private void start_simulation()
        {
            List<info_atraccio> lst = db.getAtraccions();
            foreach (info_atraccio a in lst)
            {
                var t = new StopableThread(a.temps_espera_minuts, a.capacitat, a.id, db);
                simulations.Add(t);
                t.Start();
            }
        }

        private void start_server()
        {
            //ThreadStart childref = new ThreadStart(client_atend);
            log("starting TCP/IP server");
            //IPHostEntry ipHostInfo = Dns.GetHostEntry("localhost");
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[3];
            //IPAddress ipAddress = ipHostInfo.AddressList[1];
            IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);
            sock = new Socket(ipAddress.AddressFamily,
                SocketType.Stream, ProtocolType.Tcp);
            try
            {
                sock.Bind(localEndPoint);
                sock.Listen(10);
                log("TCP/IP Server started");
                while (!stoping)
                {
                    Socket handler = sock.Accept();
                    Task.Run(() =>
                    {
                        client_atend(handler);
                    });
                }
                while (clients > 0) ;
                sock.Shutdown(SocketShutdown.Both);
                sock.Close();
            }
            catch (Exception e)
            {
                log("ERROR: error starting TCP/IP server:");
                log("\t" + e.ToString());
            }
        }

        public void Stop()
        {
            if (started)
            {
                started = false;
                log("Stopping Server");
                log("Stopping TCP/IP Server");
                stoping = true;
                log("Stopping Simulations");
                foreach (StopableThread t in simulations)
                {
                    t.Stop();
                }
                log("Simulations stoped");
                while (clients > 0) ;
                db.clear_sessions();
                log("TCP/IP Server Stoped");
                log("Server Stoped");
            }
        }

        private void client_atend(Socket handler)
        {
            log("client connected");
            clients++;
            Request req = Recive(handler);
            Serializable res;
            switch (req.function)
            {
                case "login": res = login(req); break;
                case "getInfoParcs": res = getInfoParcs(req); break;
                case "getInfoAtraccions": res = getinfoAtraccions(req); break;
                case "getPassis": res = getPassis(req); break;
                case "canAcces": res = canAcces(req); break;
                case "confirmAcces": res = confirmAcces(req); break;
                default: res = new error_obj(ERROR_CODES.INVALID_FUNCTION, "Unknown Function"); break;
            }
            handler.Send(res.serialize());
            handler.Shutdown(SocketShutdown.Both);
            handler.Close();
            clients--;
            log("client disconnected");
        }

        private Serializable getinfoAtraccions(Request req)
        {
            if (req.args.Count() == 1 /*&& req.args[0].GetType() == typeof(int)*/)
            {
                return db.getInfoAtraccions(int.Parse(string.Format("{0}", req.args[0])));
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS, "Invalid Parameters");
        }

        private Serializable getInfoParcs(Request req)
        {
            return db.getInfoParcs();
        }

        private Serializable confirmAcces(Request req)
        {
            if (req.args.Count() == 2 /*&& req.args[0].GetType() == typeof(int) && req.args[1].GetType() == typeof(int)*/)
            {

                return db.confirmarAcces(int.Parse(string.Format("{0}", req.args[0])), int.Parse(string.Format("{0}", req.args[1])));
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS, "Invalid Parameters");
        }

        private Serializable canAcces(Request req)
        {
            if (req.args.Count() == 2 /*&& req.args[0].GetType() == typeof(int) && req.args[1].GetType() == typeof(int)*/)
            {
                return db.potAccedir(int.Parse(req.args[0].ToString()), int.Parse(req.args[1].ToString()));
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS, "Invalid Parameters");
        }

        private Serializable getPassis(Request req)
        {
            if (req.args.Count() == 1 /*&& req.args[0].GetType() == typeof(int)*/)
            {
                return db.getPassis(int.Parse(req.args[0].ToString()));
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS, "Invalid Parameters");
        }

        private Serializable login(Request req)
        {
            if (req.args.Count() == 2 /*&& req.args[0].GetType() == typeof(string) && req.args[1].GetType() == typeof(string)*/)
            {
                return db.login(req.args[0].ToString(), req.args[1].ToString());
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS, "Invalid Parameters");
        }
        private void Send(Socket sok, Serializable obj)
        {
            sok.Send(obj.serialize());
        }
        private Request Recive(Socket handler)
        {
            int len = Serializable.HEADER_LENGTH;
            byte[] buf = new byte[len];
            handler.Receive(buf, len, SocketFlags.None);
            //var s=Encoding.ASCII.GetString(buf);
            len = BitConverter.ToInt32(buf, 0);
            byte[] bod = new byte[len];
            handler.Receive(bod, len, SocketFlags.None);
            return (Request)Serializable.deserialize(bod);
        }

        void log(string cont)
        {
            App.Current.Dispatcher.Invoke((Action)delegate
            {
                log_tb.AppendText(cont + Environment.NewLine);
            });
        }

        private void Start_Click(object sender, RoutedEventArgs e)
        {
            Start();
        }

        private void Stop_Click(object sender, RoutedEventArgs e)
        {
            Stop();
        }
    }
}
