using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic
{
    public interface DBInterface
    {
        void Commit();
        void Rollback();
        List<estat_atraccio> getInfo();

    }
}
