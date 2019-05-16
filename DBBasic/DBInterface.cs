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
        info_obj getInfo();
        login_obj login(string user, string pass);
        getpass_obj getPassis(int ses_id);
        canacces_obj potAccedir(int passi, int atraccio);
        confirm_obj confirmarAcces(int passi, int atraccio);
    }
}
