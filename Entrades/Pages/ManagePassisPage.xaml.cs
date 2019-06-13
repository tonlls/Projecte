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
    public sealed partial class ManagePassisPage : Page
    {
        DBInterface db;
        public ManagePassisPage()
        {
            this.InitializeComponent();
        }
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            db = (DBInterface)e.Parameter;
            lsvFullAtraccions.ItemsSource = db.getAtraccions();
            cboTipusPasi.ItemsSource = db.getTipusPasis();
            cboTipusAcces.ItemsSource = new List<String>() { "UN_SOL_US", "ILIMITAT", "UN_SOL_US_1A", "ILIMITAT_UN_1A" };
        }
        private void CboTipusPasi_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (cboTipusPasi.SelectedItem == null) return;
            tipus_passi tp = ((tipus_passi)cboTipusPasi.SelectedItem);
            if (cboTipusPasi.SelectedItem != null) lsvAtraccionsEnUnPasi.ItemsSource = db.getAtraccions(tp.id);
            txbPreu.Text = tp.preu + "";
            txbNom.Text = tp.nom;
        }

        private void AddAtraccio_Click(object sender, RoutedEventArgs e)
        {
           if(cboTipusPasi.SelectedItem==null||cboTipusAcces.SelectedItem==null|| lsvFullAtraccions.SelectedItem==null) return;
            db.addAtracciotoTipusPassi(((tipus_passi)cboTipusPasi.SelectedItem).id,((info_atraccio)lsvFullAtraccions.SelectedItem).id,cboTipusAcces.SelectedIndex+1);
            if (cboTipusPasi.SelectedItem != null) lsvAtraccionsEnUnPasi.ItemsSource = db.getAtraccions(((tipus_passi)cboTipusPasi.SelectedItem).id);
        }

        private void BtnQuitAtraccio_Click(object sender, RoutedEventArgs e)
        {
            if (cboTipusPasi.SelectedItem == null || lsvAtraccionsEnUnPasi.SelectedItem == null) return;
            db.removeAtracciotoTipusPassi(((tipus_passi)cboTipusPasi.SelectedItem).id, ((info_atraccio)lsvAtraccionsEnUnPasi.SelectedItem).id);
            if (cboTipusPasi.SelectedItem != null) lsvAtraccionsEnUnPasi.ItemsSource = db.getAtraccions(((tipus_passi)cboTipusPasi.SelectedItem).id);
        }

        private void BtnAddNewPassi_Click(object sender, RoutedEventArgs e)
        {
            db.addTipusPasi(new tipus_passi(0, txbNom.Text, float.Parse(txbPreu.Text)));
            cboTipusPasi.ItemsSource = db.getTipusPasis();
        }

        private void BtnModifiPasi_Click(object sender, RoutedEventArgs e)
        {
            if (cboTipusPasi.SelectedItem == null) return;
            tipus_passi tp=((tipus_passi)cboTipusPasi.SelectedItem);
            db.updatePasiType(tp.id,txbNom.Text,txbPreu.Text);
            cboTipusPasi.ItemsSource = db.getTipusPasis();
            txbNom.Text = "";
            txbPreu.Text = "";
            lsvAtraccionsEnUnPasi.ItemsSource = null;
            cboTipusPasi.ItemsSource = db.getTipusPasis();
        }

        private void BtnDelPasi_Click(object sender, RoutedEventArgs e)
        {
            if (cboTipusPasi.SelectedItem == null) return;
            tipus_passi tp = ((tipus_passi)cboTipusPasi.SelectedItem);
            db.delPassiType(tp.id);
            txbNom.Text = "";
            txbPreu.Text = "";
            cboTipusAcces.SelectedIndex = -1;
            cboTipusPasi.SelectedIndex = -1;
            cboTipusPasi.ItemsSource = db.getTipusPasis();
            lsvAtraccionsEnUnPasi.ItemsSource = null;

        }

        private void Page_Loaded(object sender, RoutedEventArgs e)
        {

        }
        public void validate()
        {
            if (cboTipusPasi.SelectedItem == null) btnDelPasi.IsEnabled = false;
            else btnDelPasi.IsEnabled = true;

            if (lsvFullAtraccions.SelectedItem == null || cboTipusPasi.SelectedItem == null) btnAddAtraccio.IsEnabled = false;
            else btnAddAtraccio.IsEnabled = true;

            if (lsvAtraccionsEnUnPasi.SelectedItem == null || cboTipusPasi.SelectedItem == null) btnAddAtraccio.IsEnabled = false;
            //if()
        }
    }
}
