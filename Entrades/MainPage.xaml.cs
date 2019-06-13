using DBBasic;
using Entrades.Pages;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0xc0a

namespace Entrades
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        private DBInterface db;
        public MainPage()
        {
            // var y=typeof(MySql).AssemblyQualifiedName;
            db = DBFactory.getInstance("MySqlDriver.MySql, MySqlDriver");
            InitializeComponent();
        }


        private void Entrades_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(typeof(EntradesPage),db);
        }

        private void Passis_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(typeof(PassisPage),db);
        }

        private void Manage_passis_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(typeof(ManagePassisPage),db);

        }

        private void Informe_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(typeof(InformePage),db);

        }
    }
}
