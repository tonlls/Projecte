using Nancy.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public abstract class Serializable
    {
        public static int HEADER_LENGTH=20;

        public byte[] serialize(bool header=true)
        {
            var body=Encoding.ASCII.GetBytes(new JavaScriptSerializer().Serialize(this));
            var head = new byte[HEADER_LENGTH];
            byte[] len = Encoding.ASCII.GetBytes((HEADER_LENGTH+body.Length).ToString());
            byte[] ret = new byte[HEADER_LENGTH + body.Length];
            System.Buffer.BlockCopy(len, 0, head, 0, len.Length);
            System.Buffer.BlockCopy(head, 0, ret, 0, head.Length);
            System.Buffer.BlockCopy(body, 0, ret, head.Length, body.Length);
            return ret;
        }
        /*public string serialize()
        {
            return new JavaScriptSerializer().Serialize(this);
        }*/
        public static object deserialize(string cont)
        {
            return new JavaScriptSerializer().Deserialize<info_atraccio>(cont);
        }
    }
}
