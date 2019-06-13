using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class passi
    {
        public client cli;
        public int type;

        public passi(client cli, int type)
        {
            this.cli = cli;
            this.type = type;
        }
    }
}
