using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Entrades
{
    /// <summary>
    /// Lógica de interacción para NumberInput.xaml
    /// </summary>
    public partial class NumberInput : UserControl
    {


        public int number
        {
            get { return (int)GetValue(numberProperty); }
            set { SetValue(numberProperty, value); }
        }

        // Using a DependencyProperty as the backing store for number.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty numberProperty =
            DependencyProperty.Register("number", typeof(int), typeof(NumberInput), new PropertyMetadata(0,onNumberChangedStatic));

        private static void onNumberChangedStatic(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            ((NumberInput)d).numberChanged();
        }

        private void numberChanged()
        {
            num.Text = number.ToString();
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

        private void Num_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            e.Handled = !IsTextAllowed(e.Text);
        }
        private static readonly Regex _regex = new Regex("[^0-9]+"); //regex that matches disallowed text
        private static bool IsTextAllowed(string text)
        {
            return !_regex.IsMatch(text);
        }

        private void Num_Pasting(object sender, DataObjectPastingEventArgs e)
        {
            if (e.DataObject.GetDataPresent(typeof(String)))
            {
                String text = (String)e.DataObject.GetData(typeof(String));
                if (!IsTextAllowed(text))
                {
                    e.CancelCommand();
                }
            }
            else
            {
                e.CancelCommand();
            }
        }
    }
}
