using DBBasic;
using DBBasic.Model;
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
    /// Lógica de interacción para EntradesPage.xaml
    /// </summary>
    public partial class EntradesPage : Page
    {
        private DBInterface db;
        private List<preu> preus;
        private List<info_parc> parcs;
        private List<string> categories = new List<string>() { "ADULT", "DISCAPACITAT", "SENIOR" };
        public EntradesPage(DBInterface db)
        {
            this.db = db;
            preus = db.getPreus();
            parcs = db.getInfoParcs().parcs;
            InitializeComponent();
            entradesList.ItemsSource = categories;
            parcsList.ItemsSource = parcs;
        }
        public void calculatePrice()
        {
            int days=int.Parse(dies.Text);
            var selectedParcs=parcsList.SelectedItems;
            foreach(preu p in preus)
            {
                if (p.dies == days && p.parcs == selectedParcs)
                {
                    foreach(ListViewItem x in entradesList.Items)
                    {
                        x.
                    }
                    //preu.Text = +"";
                }
            }
            return 1;
        }
    }
}
