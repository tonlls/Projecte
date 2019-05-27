using DBBasic.Model;
using DBBasic.output_obj;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic
{
    public interface DBInterface
    {
        void Commit();
        void Rollback();
        void clear_sessions();
        info_parcs_obj getInfoParcs();
        info_atraccions_obj getInfoAtraccions(int parc);
        login_obj login(string user, string pass);
        getpass_obj getPassis(int ses_id);
        canacces_obj potAccedir(int passi, int atraccio);
        confirm_obj confirmarAcces(int passi, int atraccio);
        // pel servidor, per la simulació
        List<info_atraccio> getAtraccions();
        int getCua(int atraccio);
        void updateCuaAtraccio(int atraccio,int cua);
        // per la app WPF de venta d'entrades
        List<preu> getPreus();
    }
}
