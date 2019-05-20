using Nancy.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;

namespace DBBasic.Model
{
    public abstract class Serializable
    {
        public static int HEADER_LENGTH=4;

        public byte[] serialize(bool header=true)
        {
            /*BinaryFormatter bf = new BinaryFormatter();
            byte[] body;
            using (var ms = new MemoryStream())
            {
                bf.Serialize(ms, this);
                body=ms.ToArray();
            }*/
            var body=Encoding.UTF8.GetBytes(new JavaScriptSerializer().Serialize(this));
            var head = new byte[HEADER_LENGTH];
            /*for(int i=0;i!=HEADER_LENGTH;i++)
            {
                head[i] = 0;
            }*/
            //byte[] len = Encoding.ASCII.GetBytes(body.Length+"\0");
            byte[] len = BitConverter.GetBytes(body.Length);
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
        public static Request deserialize(byte[] cont)
        {
            /*using (var memStream = new MemoryStream())
            {
                var binForm = new BinaryFormatter();
                memStream.Write(cont, 0, cont.Length);
                memStream.Seek(0, SeekOrigin.Begin);
                Serializable obj = (Serializable)binForm.Deserialize(memStream);
                return obj;
            }*/
            var sz = new JavaScriptSerializer();
            var x = Encoding.UTF8.GetString(cont);
            return sz.Deserialize<Request>(x);
        }
    }
}
