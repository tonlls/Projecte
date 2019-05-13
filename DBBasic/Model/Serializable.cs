using Nancy.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public abstract class Serializable
    {
        public string serialize()
        {
            return new JavaScriptSerializer().Serialize(this);
        }
        public object deserialize(string cont)
        {
            return new JavaScriptSerializer().Deserialize<info_atraccio>(cont);
        }
    }
}
