using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic
{
    public interface DBInterface
    {
        void Commit();
        void Rollback();
        List<info_atraccio> getInfo();
        int login(string user, string pass);
        List<passi_expres> getPassis(int ses_id);
    }
}
