using System;
using System.Threading;
using System.Threading.Tasks;

namespace Server
{
    internal class StopableThread 
    {
        private Thread _thread;
        bool _stop = false;
        public void stop()
        {
            _stop = false;
        }
        protected StopableThread()
        {
            _thread = new Thread(new ThreadStart(this.RunThread));
        }

        // Thread methods / properties
        public void Start() => _thread.Start();
        public void Join() => _thread.Join();
        public bool IsAlive => _thread.IsAlive;

        // Override in base class
        public void RunThread()
        {

        }
    }
}