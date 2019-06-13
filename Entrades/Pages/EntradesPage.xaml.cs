using DBBasic;
using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Diagnostics;
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

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace Entrades.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class EntradesPage : Page
    {
        private preu PREU;
        private DBInterface db;
        private List<preu> preus;
        private List<info_parc> parcs;
        private List<info_parc> selectedParcs=new List<info_parc>();

        private List<cat> categories = new List<cat>() { new cat("ADULT"), new cat("DISCAPACITAT"), new cat("SENIOR") };
        public class cat
        {
            String nom;
            int cant = 0;

            public cat(string nom)
            {
                this.Nom = nom;
            }

            public cat(string nom, int cant)
            {
                this.Nom = nom;
                this.Cant = cant;
            }

            public int Cant { get => cant; set => cant = value; }
            public string Nom { get => nom; set => nom = value; }
        }
        public EntradesPage()
        {
            InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            db = (DBInterface)e.Parameter;
            preus = db.getPreus();
            parcs = db.getInfoParcs().parcs;
            entradesList.ItemsSource = categories;
            parcsList.ItemsSource = parcs;
        }

        public EntradesPage(DBInterface db)
        {
            this.db = db;
            preus = db.getPreus();
            parcs = db.getInfoParcs().parcs;
            InitializeComponent();
            entradesList.ItemsSource = categories;
            parcsList.ItemsSource = parcs;
            calculatePrice();
        }
        public void calculatePrice()
        {
            int days = dies.SelectedItem==null||((ComboBoxItem)dies.SelectedItem).Content== "" ? 0 : int.Parse(((ComboBoxItem)dies.SelectedItem).Content.ToString());
            //if(days<=0) preu.Text = "UNAVALIABLE";
            var y=parcsList.SelectedItems;
            Dictionary<String, int> cantities=new Dictionary<string, int>();
            foreach (cat x in entradesList.Items)
            {
                cantities.Add(x.Nom, x.Cant);
            }
            preu p = getCuota(days, selectedParcs);
            PREU = p;
            if (p == null) preu.Text = "UNAVALIABLE";
            else preu.Text = (cantities["ADULT"]*p.preu_adult+cantities["DISCAPACITAT"]*p.preu_discapacitat+cantities["SENIOR"]*p.preu_nen_senior)+"";
            checkBuyButton();
        }

        private void checkBuyButton()
        {
            bool enable = false;
            foreach (cat x in entradesList.Items)
            {
                if (x.Cant > 0) enable = true;
            }
            if (PREU == null) enable = false;
            if (nif.Text == "") enable = false;
            if (dies.SelectedItem == null) enable = false;
            buy.IsEnabled = enable;
        }

        private preu getCuota(int dies, List<info_parc> parcs)
        {
            var ps = new List<int>();
            foreach(info_parc pa in parcs)
            {
                ps.Add(pa.id);
            }
            foreach (preu p in preus)
            {
                if (p.dies == dies && p.parcs.Except(ps).Count()==0 && p.parcs.Count()==ps.Count())
                    return p;
            }
            return null;
        }
        private void ParcsList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if(!(e.AddedItems.Count==0))selectedParcs.Add((info_parc)e.AddedItems[0]);
            if(!(e.RemovedItems.Count==0))selectedParcs.Remove((info_parc)e.RemovedItems[0]);
            calculatePrice();
        }

        private void NumberInput_ValueChanged(object sender, EventArgs e)
        {
            calculatePrice();

        }

        private void Nif_TextChanged(object sender, TextChangedEventArgs e)
        {
            calculatePrice();
        }

        private void Dies_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            calculatePrice();
        }

        private void Buy_Click(object sender, RoutedEventArgs e)
        {
            List<int> ids = new List<int>();
            for(int j=0;j != 3;j++)
            {
                cat x = (cat)entradesList.Items[j];
                for(int i = 0; i != x.Cant; i++)
                {
                    float p=0;
                    if (j == 0) p = PREU.preu_adult;
                    else if (j == 1) p = PREU.preu_discapacitat;
                    else if (j == 2) p = PREU.preu_nen_senior;
                    ids.Add(db.insertEntrada(new entrada(p, 0, nif.Text, j + 1, int.Parse(((ComboBoxItem)dies.SelectedItem).Content.ToString()), PREU.id),parcs));
                }
            }
            ObrirVendes(ids[0], ids[ids.Count() - 1]);
        }
        async void ObrirVendes(int i, int i2)
        {
            var success = await Windows.System.Launcher.LaunchUriAsync(new Uri("http://92.222.27.83:8080/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2Fm2-tllucia&reportUnit=%2Fm2-tllucia%2Fentrada&standAlone=true&j_username=m2-tllucia&j_password=47125160T&idMin=" + i + "&idMax=" + i2));
            if (success)
            {

                // URI launched

            }

        }
    }

}
