using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text.RegularExpressions;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Control de usuario está documentada en https://go.microsoft.com/fwlink/?LinkId=234236

namespace Entrades
{
    public sealed partial class NumberInput : UserControl
    {


        public event EventHandler ValueChanged;

        public int number
        {
            get { return (int)GetValue(numberProperty); }
            set { SetValue(numberProperty, value); }
        }

        // Using a DependencyProperty as the backing store for number.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty numberProperty =
            DependencyProperty.Register("number", typeof(int), typeof(NumberInput), new PropertyMetadata(0, onNumberChangedStatic));

        private static void onNumberChangedStatic(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            ((NumberInput)d).numberChanged();
        }

        private void numberChanged()
        {
            num.Text = number.ToString();
            if(ValueChanged!=null)ValueChanged(this, EventArgs.Empty);
        }

        public NumberInput()
        {
            InitializeComponent();
            number = 0;
            numberChanged();
        }

        private void Btmore_Click(object sender, RoutedEventArgs e)
        {
            number++;
            numberChanged();
        }

        private void Btminus_Click(object sender, RoutedEventArgs e)
        {
            if (number > 0)
            {
                number--;
                numberChanged();
            }
        }

        /*private void Num_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            e.Handled = !IsTextAllowed(e.Text);
        }*/
        private static readonly Regex _regex = new Regex("[^0-9]+"); //regex that matches disallowed text
        private EventHandler nbHand;

        private static bool IsTextAllowed(string text)
        {
            return !_regex.IsMatch(text);
        }

        private void Num_KeyDown(object sender, KeyRoutedEventArgs e)
        {
             
            e.Handled = !(e.Key.ToString().StartsWith("Number"));
        }

        
    }
}
