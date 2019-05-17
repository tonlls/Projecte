using DBBasic;
using DBBasic.Model;
using DBBasic.output_obj;
using System;
using System.Collections.Generic;
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
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        DBInterface db;
        private Socket sock;
        private bool stoping = false;
        private int clients = 0;
        private int threads = 0;

        public MainWindow()
        {
            InitializeComponent();
            //var y=typeof(MySqlDriver.MySql).AssemblyQualifiedName;
            db= DBFactory.getInstance("MySqlDriver.MySql, MySqlDriver");
            //var x=db.getInfo();
        }
        private void Start()
        {
            log("Server starting...");
            start_server();
            log("Server started...");
        }

        private void start_server()
        {
            log("starting TCP/IP server");
            ThreadStart childref = new ThreadStart(client_atend);
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[0];
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
            stoping = true;
        }

        private void client_atend(Socket handler)
        {
            clients++;
            Request req=Recive(handler);
            Serializable res;
            switch (req.function)
            {
                case "login":res=login(req);break;
                case "getPassis": res = getPassis(req);break;
                case "canAcces": res = canAcces(req); break;
                case "confirmAcces": res = confirmAcces(req); break;
                default:res = new error_obj(ERROR_CODES.INVALID_FUNCTION, "Unknown Function");break;
            }
            handler.Send(res.serialize());
            handler.Shutdown(SocketShutdown.Both);
            handler.Close();
            clients--;
        }

        private Serializable confirmAcces(Request req)
        {
            if (req.args.Count() == 2 && req.args[0].GetType() == typeof(int) && req.args[1].GetType() == typeof(int))
            {
                return db.confirmarAcces((int)req.args[0], (int)req.args[1]);
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS,"Invalid Parameters");
        }

        private Serializable canAcces(Request req)
        {
            if (req.args.Count() == 2 && req.args[0].GetType() == typeof(int) && req.args[1].GetType() == typeof(int))
            {
                return db.potAccedir((int)req.args[0], (int)req.args[1]);
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS,"Invalid Parameters");
        }

        private Serializable getPassis(Request req)
        {
            if (req.args.Count() == 1 && req.args[0].GetType() == typeof(int))
            {
                return db.getPassis((int)req.args[0]);
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS,"Invalid Parameters");
        }

        private Serializable login(Request req)
        {
            if (req.args.Count() == 2&&req.args[0].GetType()==typeof(string)&&req.args[1].GetType()==typeof(string))
            {
                return db.login((string)req.args[0], (string)req.args[1]);
            }
            return new error_obj(ERROR_CODES.INVALID_PARAMETERS,"Invalid Parameters");
        }
        private void Send(Socket sok,Serializable obj)
        {
            sok.Send(obj.serialize());
        }
        private Request Recive(Socket handler)
        {
            int len = Serializable.HEADER_LENGTH;
            byte[] buf= new byte[len];
            handler.Receive(buf, len,SocketFlags.None);
            return (Request)Serializable.deserialize(buf);
        }

        void log(string cont)
        {
            log_tb.AppendText(cont + "\n");
        }
    }
}
