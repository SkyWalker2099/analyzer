package lexical_Analuzer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lexical_Analuzer.syntactic_analyzer.Item;

public class LR_analyzer {

	static List<Character> digit = Arrays.asList('1','2','3','4','5','6','7','8','9');
	static String letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	static List<Character> letters = new ArrayList<>();
	static List<Character> operater = Arrays.asList('*','/','%','+','-','=','>','<','!','&','|','?');
	static List<Character> delimiter = Arrays.asList(':',';',',');
	static List<Character> xx = Arrays.asList('[',']','{','}','(',')');
	static List<String> reserved_word = Arrays.asList("cout","cin","int","double","float","endl","for","switch","while","string","void");
	static List<String> operators = Arrays.asList("<=",">=","<",">","=","!=","==",">>","<<","++","--","+=","-=","%=","/=","*=","+","-","*","/","%","&&","||","?");
	
	static JFrame frame = null;
	static JPanel panel = new JPanel();
	static JTextArea textArea1 = new JTextArea(45,45);
	static JTextArea textArea2 = new JTextArea(45,45);
	static JButton button1 = new JButton("开始分析");
	
	static List<Integer> state_endable = Arrays.asList(1,2,3,5,7,9,13,14,18,19,22,23,24,27,30);
	
	static List<Item> items = new ArrayList<>();
	static int next_index = 1;
	
	static public class Item{
		public Item() {
			// TODO 自动生成的构造函数存根		
		}
		public String string = "";
		public String label = "";
		public int next_item = -1;
	}
		
	public  static void analyse(String code) {
		items.clear();
		next_index = 0;
	   	//List<String> symbols  = new ArrayList<>();
	   	String[] symbols  = code.split("\n");
	   	int line_num = 0;
	   	for (String line :symbols) {
	   		line_num = line_num + 1;
	   		if(line != "\n") {
	   			line = line.replaceAll("\n", "");
	   			GetToken(line,line_num);
	   		}
	   	}
	   	
 
	   	
	   }
	   
	public static int ss(char n) {
	   	 if( n == '0') {
	   		 return 0;
	   	 } else if( digit.contains(n)) {
	   		 return 1;
	   	 }else if (n == 'x' || n == 'X') {
				return 6;
			}else if(letters.contains(n)) {
	   		 return 2;
	   	 }else if (operater.contains(n)) {
				return 3;
			}else if(delimiter.contains(n)) {
				return 4;
			}else if (n == '.') {
				return 5; //小数点
			}else {
				return -1;
			}
	   }
	      
	   public static void GetToken(String line, int line_num) {
	   	List<Character> tar = new ArrayList<>();
	   	int state = 0;
	   	line = line + " ";  
	   	for(char i: line.toCharArray()) {
	   	 
	   		
	   		int t = ss(i);
	   		boolean flag = false;
	   		int s  = next_state(state, t);
	   		 
	   		
	   		if(s == -1) {
	   			 
	   			
	   			next_index = next_index +1;
	   			Item item = new Item();
	   			
	   			switch (state) {					
					case 1:
						 
								
						item.string = toStr(tar);
						item.label = "num";
						item.next_item = next_index;
						items.add(item);
						
						break;
					case 2:
						 
						
					 
						item.string = toStr(tar);
						item.label = "num";
						item.next_item = next_index;
						items.add(item);
						
						break;
					case 4:
						 
						
						item.string = toStr(tar);
						item.label = "num";
						item.next_item = next_index;
						items.add(item);
						
						break;
					case 3:
					 
						
						item.string = toStr(tar);
						item.label = "num";
						item.next_item = next_index;
						items.add(item);
						
						break;
					case 7:
						 
						
						item.string = toStr(tar);
						item.label = "num";
						item.next_item = next_index;
						items.add(item);
						
						break;
					case 8:
					 
						String string = toStr(tar);
						if(reserved_word.contains(string)) {
						 
					 
							item.string = toStr(tar);
							item.label = "reserved";
							item.next_item = next_index;
							items.add(item);
							
						}else {
							 
						 
							item.string = toStr(tar);
							item.label = "id";
							item.next_item = next_index;
							items.add(item);
							
						}
						
						break;
					case 9:
						String string2 = toStr(tar);
						try {
							if(operators.contains(string2)) {
							 
							
							item.string = toStr(tar);
							item.label = "reserved";
							item.next_item = next_index;
							items.add(item);
							
						}else if("//".equals(string2.substring(0, 2))) {
							flag = true;
						}
						else {
							System.out.println(string2+"\t\t\t此处出错"+"位于第"+line_num+"行\n");
						 
						}
						} catch (Exception e) {
							System.out.println(line_num+"\n");
							e.printStackTrace();
							// TODO: handle exception
						}
						break;
					case 10:
						
						 
						
						item.string = toStr(tar);
						item.label = "delimiter";
						item.next_item = next_index;
						items.add(item);
						
						break;
					default:
						break;
					}
	   			
	   			tar.clear();
	   			
	   			 state =  next_state(0, t);
	   			if(state == -1) {
	   				state = 0;
	   			}else {
						tar.add(i);
					}
	   			
	   			
	   			
	   			
	   		}else {
					state = s;
					tar.add(i);
				}
	   		
	   		
	   		
	   		
	   		if(flag)
	   			break;
	   		
	   		if(xx.contains(i)) {
	   			next_index = next_index+1;
	   			Item item1 = new Item();
	   			item1.string = ""+i;
	   			item1.label = "spliter";
	   			item1.next_item = next_index;
	   			items.add(item1);
	   		}
	   		
	   	}
	   }
	   
	   public static String toStr(List<Character> list) {
	   	String str = "";
	   	for(char i : list) {
	   		str = str + i;
	   	}
	   	return str;
	   }
	   
	   public static void read_stack(Stack<Item> stack) {
		   if(!stack.isEmpty()) {
			   Item item = stack.pop();
			   System.out.print(item.string+" ");
			   textArea2.append(item.string+" ");
			   read_stack(stack);	  
			   stack.push(item);
		   }
	}
	   
	   public static int next_state(int state, int  t) {
	   	
	   	int next = -1;
	   	
	   	switch (state) {
			case 0:
				switch (t) {
				case 1:
					next = 1;
					break;
				case 0:
					next = 2;
					break;
				case 2:
					next = 8;
					break;
				case 3:
					next = 9;
					break;
				case 4:
					next = 10;
					break;
				default:
					break;
				}
				break;
			
				
			case 1:
				switch (t) {
				case 1:
					next = 1;
					break;
				case 0:
					next = 1;
					break;
				case 5:
					next = 4;
					break;
				default:
					break;
				}
				break;
			
			case 4:
				switch (t) {
				case 1:
					next = 4;
					break;
				case 0:
					next = 4;
					break;
				default:
					break;
				}
				break;
			
			case 2:
				switch (t) {
				case 1:
					next = 3;
					break;
				case 6:
					next = 6;
					break;
				case 0:
					next = 2;
					break;
				default:
					break;
				}
				break;
			
			case 3:
				switch (t) {
				case 1:
					next = 3;
					break;
				case 0:
					next = 3;
					break;
				default:
					break;
				}
				break;
			
			case 6:
				switch (t) {
				case 1:
					next = 7;
					break;
				default:
					break;
				}
				break;
				
			case 7:
				switch (t) {
				case 1:
					next = 7;
					break;
				case 0:
					next = 7;
					break;
				case 7:
					next = 7;
					break;
				default:
					break;
				}
				break;
			
			case 8:
				switch (t) {
				case 1:
					next = 8;
					break;
				case 2:
					next = 8;
					break;
				case 0:
					next = 8;
					break;
				default:
					break;
				}
				break;
				
			case 9:
				switch (t) {
				case 3:
					next = 9;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			
			}
	   	
	   	return next;
	   }

		public static void main(String[] args) {
			for ( Character i :  letter.toCharArray()) {
				letters.add(i);
			}			
			frame = new JFrame("LR语法分析器");
		   	frame.setSize(1200, 900);

		   	JScrollPane sp1 = new  JScrollPane(textArea1);
		   	JScrollPane sp2 = new JScrollPane(textArea2);
		 			   	
		   	panel.add(sp1);
		   	panel.add(sp2);
		   	panel.add(button1);
		   	
		   	frame.add(panel);
		   	frame.setVisible(true);
		   	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   	button1.addActionListener(new  ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						 String code = textArea1.getText();
						 textArea2.setText("");
						 analyse(code);
						 LR_analyzer();
					}
				});		
	}
	   
		public static  List<String> First(String  string) {
			List<String> result = new ArrayList<>();
			switch (string) {
			case "bool":
				result = Arrays.asList("id","num");
				break;
			case "E1":
				result = Arrays.asList("+");
				break;
			case "E":
				result = Arrays.asList("id","num");
				break;
			case "stmt":
				result = Arrays.asList("id","while","{");
				break;
			case "stmts":
				result = First("stmt");
				break;
			case "block":
				result.add("{");
				break;
			default:
				break;
			}
			
			return result;
		}
		//first函数要重新写
		
		public static void LR_analyzer() {
			Stack<Item> stack = new Stack<>();
			Stack<Item> string_stack = new Stack<>();
			Stack<Integer> state_stack = new Stack<>();
			Item ii = new Item();
			ii.string = "#";
			Item iiItem = new Item();
			iiItem.string = "#";
			stack.push(iiItem);
			string_stack.push(ii);
			Collections.reverse(items);
			for(Item item: items) {
				string_stack.push(item);
			}
 
			int state = 0;
			state_stack.push(0);
			while(true) {
				for(int i: state_stack) {
					System.out.print(i);
					textArea2.append(Integer.toString(i)+",");
				}
				System.out.print("\t");
				textArea2.append("      ");
				for(Item item: stack) {
					System.out.print(item.string+" ");
					textArea2.append(item.string+" " );
				}
				System.out.print("\t");
				textArea2.append("      ");
				read_stack(string_stack);
				System.out.println("");
				
				state = state_stack.peek();
				textArea2.append("\n");
				System.out.println(state);
				if(stack.peek().string == "Program" && string_stack.peek().string == "#") {
					textArea2.append("done");
					break;
				}
				//////////////////////////////////////////////////////////// 如果此状态含有可以归约的。。
				if(state_endable.contains(state)) {
					System.out.println("是可归约状态");
					boolean flag = false;
					boolean n = false;
					System.out.print(state);
					System.out.println(" "+ string_stack.peek().string);
					int state_num;
					if(string_stack.peek().label == "id" || string_stack.peek().label == "num") {
						state_num = can_parse(state, string_stack.peek().label);
					}else {
						state_num = can_parse(state, string_stack.peek().string);
					}
					
					if(state_num != -1) {
						n = true;
						System.out.println("满足可归约条件");
						String string = parsed_state(state);
						//归约后的符号
						System.out.println(state_num);
						while(state_num!=0) {
							state_stack.pop();
							state_num -=1;
							stack.pop();
						}
//						if(state_stack.peek() == 0 && string_stack.peek().string.equals("#")) {
//							flag  = true;
//						}
						state = parse(state_stack.peek(), string );
						System.out.println(Integer.toString(state_stack.peek())+" "+string+" "+Integer.toString(state));
						System.out.println("---------------------------------------");
						 state_stack.push(state);
				 
						 Item item = new Item();
						 item.string = string;
						 stack.push(item);
					}
					if(flag)
						break;
					if(n)
						continue;
				}
				/////////////////////////////////////////////////////////////
				 
				String string = new String();
				if(string_stack.peek().label.equals("id") || string_stack.peek().label.equals("num")) {
					 string = string_stack.peek().label;
				}else {
					string = string_stack.peek().string;
				}
				System.out.print(state);
				int next_state = parse(state, string);
				System.out.print(" "+string+" "+Integer.toString(next_state)+"\n"+"-----------------------------"+"\n");
				if(next_state == -1) {
					System.err.print("wrong");
					break;
				}else {
					state_stack.push(next_state);
					stack.push(string_stack.pop());
				}
				
			}
			
		}
	
		
		public static int  parse(int state, String string) {
			int result = -1;
			switch (state) {
			case 0:
				switch (string) {
				case "block":
					result = 1;
					break;
				case "{":
					result = 3;
					break;
				default:
					break;
				}
				break;
////////////////////////////////////////////////////////
			case 3:
				switch (string) {
				case "stmts":
					result = 4;
					break;
				case "stmt":
					result = 2;
					break;
				case "id":
					result = 6;
					break;
				case "while":
					result = 8;
					break;
				case "block":
					result = 7;
					break;
				case "{":
					result = 10;
					break;
				default:
					break;
				}
				break;
				////////////////////////////////////////////
			case 4:
				switch(string) {
				case "}":
					result = 9;
					break;
				default:
					break;
				}
				break;
				///////////////////////////////////////////////
			case 2:
				switch (string) {
				case "stmts":
					result = 5;
					break;
				case "stmt":
					result = 2;
					break;
				case "id":
					result = 6;
					break;
				case "while":
					result = 8;
					break;
				case "block":
					result = 7;
					break;
				case "{":
					result = 10;
					break;
				default:
					break;
				}
				break;
				//////////////////////////////////////////////
			case  8:
				switch (string) {
				case "(":
					result = 12;
					break;
				default:
					break;
				}
				break;
				///////////////////////////////////////////////
			case 6:
				switch (string) {
				case "=":
					result = 11;
					break;
				default:
					break;
				}
				break;
				////////////////////////////////////////////
			case 10:
				switch (string) {
				case "stmts":
					result = 4;
					break;
				case "stmt":
					result = 2;
					break;
				case  "id":
					result = 6;
					break;
				case "while":
					result = 8;
					break;
				case "block":
					result = 7;
					break;
				case "{":
					result = 10;
					break;
				default:
					break;
				}
				break;
				////////////////////////////////////////////
			case 11:
				switch (string) {
				case "E":
					result = 17;
					break;
				case "T":
					result = 19;
					break;
				case "id":
					result = 13;
					break;
				case "num":
					result = 14;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case 17:
				switch (string) {
				case ";":
					result = 18;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case  12:
				switch (string) {
				case "bool":
					result = 16;
					break;
				case "E":
					result = 28;
					break;
				case "T":
					result = 19;//
					break;
				case "id":
					result = 13;
					break;
				case "num":
					result = 14;
					break;
				default:
					break;
				}
				break;
				///////////////////////////////////////////////
			case 19:
				switch (string) {
				case "E1":
					result = 22;
					break;
				case "+":
					result = 25;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case 25:
				switch (string) {
				case "T":
					result = 24;
					break;
				case "id":
					result = 13;
					break;
				case "num":
					result = 14;
					break;
				default:
					break;
				}
				break;
				//////////////////////////////////////////
			case 24:
				switch (string) {
				case "E1":
					result = 23;
					break;
				case "+":
					result = 25;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case 16:
				switch (string) {
				case ")":
					result = 20;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case 20:
				switch (string) {
				case "stmt":
					result = 27;
					break;
				case "id":
					result = 6;
					break;
				case "while":
					result = 8;
					break;
				case "block":
					result = 7;
					break;
				case "{":
					result = 10;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			case 28:
				switch (string) {
				case ">=":
					result = 29;
					break;
				case "<=":
					result = 29;
					break;
				default:
					break;
				}
				break;
				////////////////////////////////////////////
			case 29:
				switch (string) {
				case "E":
					result = 30;
					break;
				case "T":
					result = 19;
					break;
				case "id":
					result = 13;
					break;
				case "num":
					result = 14;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////////////////////
			default:
				break;
			}
			
			return result;
		}
		//返回下一个状态
//	static List<Integer> state_endable = Arrays.asList(1,2,3,5,7,9,13,14,18,19,22,27,30);
		
		public static int can_parse(int state, String string) {
			int result = -1;
			
			switch (state) {
			case 24:
				if(First("stmts").contains(string)|| string.equals(";")||string.equals(">=")||string.equals("<=")||string.equals(")")){
					 result = 0;
				 }
				 break;
			///////////////////////////
			case 1:
				switch (string) {
				case "#":
					result = 1;
					break;
				default:
					break;
				}
				break;
				////////////////////////////
			case 2:
				switch (string) {
				case "}":
					result = 0;
					break;
				default:
					break;
				}
				break;
				/////////////////////////////
			case 3:
				switch (string) {
				case "{":
					result = 0;
					break;
				default:
					break;
				}
				break;
				////////////////////////////
			case 5:
				switch (string) {
				case "}":
					result = 2;
					break;
				default:
					break;
				}
				break;
				////////////////////////////
			case 7:
				if(First("stmts").contains(string) || string.equals("}")){
					result = 1;
				}
				break;
				////////////////////////////
			case  9:
				if(string.equals("#") || First("stmts").contains(string) || string.equals("}")) {
					result = 3;
				}
				break;
				////////////////////////////
			case 13:
				if(First("E1").contains(string)|| string.equals(";")||string.equals(">=")||string.equals("<=")||string.equals(")"))
					result = 1;
				break;
				////////////////////////////
			case 14:
				if(First("E1").contains(string)|| string.equals(";")||string.equals(">=")||string.equals("<=")||string.equals(")"))
					result = 1;
				break;
				////////////////////////////
			case 18:
				if(First("stmts").contains(string) || string.equals("}")) {
					result = 4;
				}
				break;
				////////////////////////////
			case 19:
				if(First("stmts").contains(string)|| string.equals(";")||string.equals(">=")||string.equals("<=")||string.equals(")"))
					result = 0;
				 break;
				 ////////////////////////////
			case  22:
				if(string.equals(";") || string.equals(">=")|| string.equals("<=")|| string.equals(")") || string.equals("+")) {
					result = 2;
				}
				break;
				///////////////////////////
			case 23:
				if(First("stmts").contains(string) || string.equals(";")||string.equals(">=")||string.equals("<=")||string.equals(")") ) {
					result = 3;
				}
				break;
				///////////////////////////
			case 27:
				if(First("stmts").contains(string) || string.equals("}")) {
					result = 5;
				}
				break;
				////////////////////////////
			case 30:
				if(string.equals(")")){
					result = 3;
				}
				break;
				////////////////////////////
			default:
				break;
			}
			
			return result;
		}
		//如果后缀满足可以归约，则返回状态栈中要去掉的个数
		public static String parsed_state(int i) {
			String r = null;
			switch (i) {
			case 24:
				r = "E1";
				break;
			case 1:
				r =  "Program";
				break;
			case 2:
				r =  "stmts";
				break;
			case 3:
				r =  "stmts";
				break;
			case 5:
				r = "stmts";
				break;
			case 7:
				r = "stmt";
				break;
			case 9:
				r = "block";
				break;
			case 13:
				r = "T";
				break;
			case 14:
				r = "T";
				break;
			case 18:
				r = "stmt";
				break;
			case 19:
				r = "E1";
				break;
			case 22:
				r = "E";
				break;
			case 23:
				r = "E1";
				break;
			case 27:
				r = "stmt";
				break;
			case 30:
				r = "bool";
				break;
			default:
				break;
			}
			return r;
		}
		//返回归约成什么状态；
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
