using DBBasic;
using DBBasic.Model;
using Entrades.Pages;
using MySqlDriver;
using System;
using System.Collections.Generic;
using System.Linq;
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

namespace Entrades
{ 
    public partial class MainWindow : Window
    {
        private DBInterface db;
        public MainWindow()
        {
           // var y=typeof(MySql).AssemblyQualifiedName;
            db = DBFactory.getInstance("MySqlDriver.MySql, MySqlDriver");
            InitializeComponent();
            //frame.Navigate(new EntradesPage(db));
            
            
            //db.getCategoriesEntrades();
        }
        

        private void Entrades_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(new EntradesPage(db));
        }

        private void Passis_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(new PassisPage(db));
        }

        private void Manage_passis_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(new ManagePassisPage(db));

        }

        private void Informe_Click(object sender, RoutedEventArgs e)
        {
            frame.Navigate(new InformePage());

        }
    }
}
