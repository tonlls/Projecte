using DBBasic;
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
    /// <summary>
    /// Lógica de interacción para PassisPage.xaml
    /// </summary>
    public partial class PassisPage : Page
    {
        DBInterface db;
        public PassisPage(DBInterface db)
        {
            this.db = db;
            InitializeComponent();
            //type.ItemsSource=db.get
        }
    }
}
