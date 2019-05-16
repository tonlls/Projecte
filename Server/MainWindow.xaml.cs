using DBBasic;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
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

        public MainWindow()
        {
            InitializeComponent();
            var y=typeof(MySqlDriver.MySql).AssemblyQualifiedName;
            db= DBFactory.getInstance("MySqlDriver.MySql, MySqlDriver");
            var x=db.getInfo();
        }
        private void Start()
        {
            log("Server starting...");
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[0];
            IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);
            sock = new Socket(ipAddress.AddressFamily,
                SocketType.Stream, ProtocolType.Tcp);
            try
            {
                sock.Bind(localEndPoint);
                sock.Listen(10);
                log("Server started");
                while (true)
                {
                    Socket handler = sock.Accept();
                    Task.Run(() =>
                    {
                        //client_atend(handler);
                    });
                }
            }
            catch (Exception e)
            {
                log("ERROR: error starting server:");
                log("\t" + e.ToString());
            }
        }

        void log(string cont)
        {
            log_tb.AppendText(cont + "\n");
        }
    }
}
