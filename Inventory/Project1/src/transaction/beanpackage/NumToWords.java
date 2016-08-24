package transaction.beanpackage;

public class NumToWords {
        String string;
           private static String amount;
        String st1[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
                        "Eight", "Nine", };
        String st2[] = { "Hundred", "Thousand", "Lakh", "Crore" };
        String st3[] = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen", };
        String st4[] = { "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
                        "Eighty", "Ninty" };

        public NumToWords()
        {
              
        }
        
        
        
         public String NumToWord(String  a)
    {
        System.out.println("A value is \t"+a);
        amount="";
        //convert(a);
        String str2 ="";
                
//                Scanner input = new Scanner(System.in);
//                System.out.print("Enter Number: ");
                
               // String amt=input.next();
                String amt=a;
               System.out.println(amt+"\t amount from main");
               
               
    int rupees = Integer.parseInt(amt.split("\\.")[0]);
        String str1 =  convert(rupees);
        str1 += " Rupees & ";
        int paise = Integer.parseInt(amt.split("\\.")[1]);
  if(paise!=0){
  str2 += " and";
        str2 = convert(paise);
        str2 += " Paise";
  }
               
  
  String nw = str1+str2+" Only";
        System.out.println(""+nw);
                //System.out.println(str1+str2+" Only");
 return nw;
    }
         
        public String convert(int number) {
                int n = 1;
                int word;
                string = "";
                System.out.println(number+"\t number");
                
                while (number != 0) {
                        switch (n) {
                        case 1:
                                word = number % 100;
                                pass(word);
                                if (number > 100 && number % 100 != 0) {
                                        show("and ");
                                }
                                number /= 100;
                                break;

                        case 2:
                                word = number % 10;
                                if (word != 0) {
                                        show(" ");
                                        show(st2[0]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 10;
                                break;

                        case 3:
                                word = number % 100;
                                if (word != 0) {
                                        show(" ");
                                        show(st2[1]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        case 4:
                                word = number % 100;
                                if (word != 0) {
                                        show(" ");
                                        show(st2[2]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        case 5:
                                word = number % 100;
                                if (word != 0) {
                                        show(" ");
                                        show(st2[3]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        }
                        n++;
                }
                System.out.println("String \t"+string);
                return string;
        }

        public void pass(int number) {
                int word, q;
                if (number < 10) {
                        show(st1[number]);
                }
                if (number > 9 && number < 20) {
                        show(st3[number - 10]);
                }
                if (number > 19) {
                        word = number % 10;
                        if (word == 0) {
                                q = number / 10;
                                show(st4[q - 2]);
                        } else {
                                q = number / 10;
                                show(st1[word]);
                                show(" ");
                                show(st4[q - 2]);
                        }
                }
        }

        public void show(String s) {
                String st;
                st = string;
                string = s;
                string += st;
        }

        public static void main(String[] args) {
            
      //   new NumToWords().NumToWord("123445.789");
        }
}
