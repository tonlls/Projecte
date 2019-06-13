using DBBasic;
using DBBasic.Model;
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

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace Entrades.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class PassisPage : Page
    {
        DBInterface db;
        List<tipus_passi> t_pas;
        public PassisPage()
        {
            this.InitializeComponent();
        }
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            db = (DBInterface)e.Parameter;
            t_pas=db.getTipusPasis();
            type.ItemsSource = t_pas;
            validate();
           /* preus = db.getPreus();
            parcs = db.getInfoParcs().parcs;
            entradesList.ItemsSource = categories;
            parcsList.ItemsSource = parcs;*/
        }
        private void Page_Loaded(object sender, RoutedEventArgs e)
        {

        }

        private void Type_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            checkPreu();
        }
        void checkPreu()
        {
            if (dies.SelectedIndex == -1) dies.SelectedIndex = 0;
            preu.Text = ((tipus_passi)type.SelectedItem).preu + "";
            total.Text = ((tipus_passi)type.SelectedItem).preu * dies.SelectedIndex + 1 + "";
        }
        private void Dies_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            checkPreu();
        }

        private void BuyPasi_Click(object sender, RoutedEventArgs e)
        {
            checkPreu();
            passwd.Text = RandomString(7);
            db.insertPassi(new passi(new client(nif.Text, passwd.Text, nom.Text, cognom1.Text, cognom2.Text), ((tipus_passi)type.SelectedItem).id));
        }

        private void Cancel_Click(object sender, RoutedEventArgs e)
        {

        }
        private static Random random = new Random();
        public static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
              .Select(s => s[random.Next(s.Length)]).ToArray());
        }
        public bool checkBuy()
        {
            if (nif.Text == "") return false;
            if (nom.Text == "") return false;
            if (dies.SelectedIndex == -1) return false;
            if (type.SelectedIndex == -1) return false;
            return true;
        }
        public void validate()
        {
            buyPasi.IsEnabled = checkBuy();
        }
        private void Preu_TextChanged(object sender, TextChangedEventArgs e)
        {
            validate();
        }

        private void Cognom2_TextChanged(object sender, TextChangedEventArgs e)
        {
            validate();

        }

        private void Cognom1_TextChanged(object sender, TextChangedEventArgs e)
       {
            validate();

        }

        private void Nom_TextChanged(object sender, TextChangedEventArgs e)
        {
            validate();

        }

        private void Nif_TextChanged(object sender, TextChangedEventArgs e)
        {
            validate();

        }
    }
}
