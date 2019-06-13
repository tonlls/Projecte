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
    public sealed partial class InformePage : Page
    {
        static DBInterface db;
        public InformePage()
        {
            this.InitializeComponent();
        }
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            db = (DBInterface)e.Parameter;
            List<info_parc> p = db.getInfoParcs().parcs;
            p.Add(new info_parc(-1, "All", ""));
            parcs.ItemsSource = p;
        }
        private void Run_Click(object sender, RoutedEventArgs e)
        {
            runReportAsync(((info_parc)parcs.SelectedItem).id);
        }
        private async void runReportAsync(int id)
        {
            if(id==-1)
                await Windows.System.Launcher.LaunchUriAsync(new Uri("http://92.222.27.83:8080/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2Fm2-tllucia&reportUnit=%2Fm2-tllucia%2Fvendes&standAlone=true&j_username=m2-tllucia&j_password=47125160T"));
            else
                await Windows.System.Launcher.LaunchUriAsync(new Uri("http://92.222.27.83:8080/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2Fm2-tllucia&reportUnit=%2Fm2-tllucia%2Fvendes&standAlone=true&j_username=m2-tllucia&j_password=47125160T&id=" + id));
        }
        //
    }
}
