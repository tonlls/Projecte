using DBBasic;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace Server
{
    public class StopableThread 
    {
        private static DBInterface _db;
        private Thread _thread;
        bool _stop = false;
        private int _time;
        private int _capacity;
        private int _atraccio;

        public void Stop()
        {
            _stop = true;
        }
        public StopableThread(float time,int capacity,int atraccio,DBInterface db)
        {
            _time =(int) time* 60000;
            _capacity = capacity;
            _atraccio = atraccio;
            _db = db;
            _thread = new Thread(new ThreadStart(this.RunThread));
        }

        // Thread methods / properties
        public void Start() => _thread.Start();
        public void Join() => _thread.Join();
        public bool IsAlive => _thread.IsAlive;

        // Override in base class
        public void RunThread()
        {
            while (!_stop)
            {
                int persones = _db.getCua(_atraccio);
                Thread.Sleep(_time);
                _db.updateCuaAtraccio(_atraccio,_db.getCua(_atraccio) - persones);
            }
        }
    }
}