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
        List<info_atraccio> getAtraccions(int tipus_passi);
        int getCua(int atraccio);
        void updateCuaAtraccio(int atraccio,int cua);
        // per la app WPF de venta d'entrades
        List<preu> getPreus();
        int insertEntrada(entrada e, List<info_parc> parcs);
        void insertPassi(passi p);
        List<tipus_passi> getTipusPasis();
        void addTipusPasi(tipus_passi tp);
        void addAtracciotoTipusPassi(int pas, int atraccio, int acces);
        void removeAtracciotoTipusPassi(int pas, int atraccio);
        void updatePasiType(int id,string name, string preu);
        bool delPassiType(int id);
        //List<>
        //List<categoria_entrada> getCategoriesEntrades();
        //info_atraccio getAtraccio(int id);
    }
}
