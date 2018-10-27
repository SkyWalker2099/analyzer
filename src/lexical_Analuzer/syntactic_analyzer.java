package lexical_Analuzer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class syntactic_analyzer {
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
//				String string = "{ i = 2; while (i <= 100) { sum = sum +1; i = i+2;} }";
//				analyse(string);
//				for(Item item: items) {
//					System.out.println(item.string+"\t"+item.label);
//				}
//				
//				syntatic_analyse();
				
				frame = new JFrame("语法分析器");
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
							 syntatic_analyse();
						}
					});		
		}
		   
			public static  List<String> First(String  string) {
				List<String> result = new ArrayList<>();
				switch (string) {
				case "block":
					result.add("{");
					break;
				case "stmts":
					result = First("stmt");
					break;
				case "stmt":
					result = Arrays.asList("id","if","while","do","break","{");
					break;
				case "stmt1":
					result.add("else");
					result.add("#");
					break;
				case "bool":
					result = Arrays.asList("(","id","num");
					break;
				case "bool1":
					result = Arrays.asList("<","<=",">",">=");
					break;
				case "expr":
					result = Arrays.asList("(","id","num");
					break;
				case "expr1":
					result = Arrays.asList("+","-");
					break;
				case "term":
					result = Arrays.asList("(","id","num");
					break;
				case "term1":
					result = Arrays.asList("*","/");
					break;
				case "factor":
					result = Arrays.asList("(","id","num");
					break;
				default:
					break;
				}
				
				return result;
			}
			
			public static List<String> a_lone_line(String string_a, String string_b) {
				 String[] result = null;
				switch (string_a) {
				case "block":
					switch (string_b) {
					case "{":
						result = "{ stmts }".split(" ");
						break;
					default:
						break;
					}
					break;
/////////////////////////////////////////
				case "stmt":
					switch (string_b) {
					case "id":
						result = "id = expr ;".split(" ");
						break;
					case "while":
						result = "while ( bool ) stmt".split(" ");
						break;
					case "do":
						result = "do stmt while ( bool )".split(" ");
						break;
					case "if":
						result = "if ( bool ) stmt stmt1".split( " ");
						break;
					case "break":
						result = "break".split(" ");
						break;
					default:
						break;
					}
					if(First("block").contains(string_b)) {
						result = "{ stmts }".split(" ");
					}
					break;
					///////////////////////////////
				case "stmts":
					if(First("stmt").contains(string_b)) {
						result = "stmt stmts".split(" ");
					}else {
						result = "#".split( " ");
					}
					break;
					////////////////////////////////
				case "stmt1":
					if(string_b.equals("else")) {
						result = "else stmts".split( " ");
					}else {
						result = "#".split( " ");
					}
					break;
					/////////////////////////////////
				case "bool":
					if(First("expr").contains(string_b)) {
						result = "expr bool1".split( " ");
					}
					break;
					///////////////////////////////
				case "bool1":
					switch (string_b) {
					case "<":
						result = "< expr".split( " ");
						break;
					case "<=":
						result = "<= expr".split( " ");
						break;
					case ">":
						result = "> expr".split( " ");
						break;
					case ">=":
						result = ">= expr".split( " ");
						break;
					default:
						result = "#".split( " ");
						break;
					}
					/////////////////////////////////
				case "expr":
					if(First("term").contains(string_b)) {
						result = "term expr1".split( " ");
					}
					break;
					/////////////////////////////////
				case "expr1":
					if(string_b.equals("+")) {
						result = "+ term expr1".split( " ");
					}else if (string_b.equals("-")) {
						result = "- term expr1".split( " ");
					}else {
						result = "#".split( " ");
					}
					 break;
					/////////////////////////////////
				case "term":
					if(First("factor").contains(string_b)) {
						result = "factor term1".split( " ");
					}
					break;
					////////////////////////////////
				case "term1":
					if(string_b.equals("*")) {
						result = "* factor term1".split( " ");
					}else if (string_b.equals("/")) {
						result = "/ factor term1".split( " ");
					}else {
						result = "#".split( " ");
					}
					break;
					///////////////////////////////////
					case "factor":
						if(string_a.equals("(")) {
							result = "( expr )".split( " ");
						}else if (string_b.equals("id")) {
							result = "id".split(" ");
						}else if(string_b.equals("num")){
							result = "num".split( " ");
						}
						break;
					 ////////////////////////////////
				default:
					break;
				}
				
				
				 List<String> rList = new ArrayList<>();
				 if(result == null) {
					 return null;
				 }
				 for(String string : result) {
					 rList.add(string);
				 }
				 return rList;
			}
			
			
			public static void syntatic_analyse() {
				int location1 = 0;
				int location2 = 0;
				Stack<Item> stack = new Stack<>();
				Stack<Item> saved_stack = new Stack<>();
				Stack<Item> string_stack = new Stack<>();
				Collections.reverse(items);
				for(Item item: items) {
					System.out.println(item.string);
					string_stack.push(item);
				}
				
			 
				Item item = new Item();
				item.string = "block";
				item.next_item = -1;
				stack.push(item);
				
		 
			 
				while(location1 != -1) {
					if(stack.isEmpty())
						break;
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println(stack.peek().string+" "+string_stack.peek().string);
					 
					if(stack.peek().string == "#") {
						stack.pop();
						for(Item item2:saved_stack) {
							System.out.print(item2.string+" ");
							textArea2.append(item2.string+" ");
						}
					read_stack(stack);
					textArea2.append("\n");
						System.out.println("");
					 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						continue;
					}
					
					if(stack.peek().string.equals(string_stack.peek().string)|| stack.peek().string.equals(string_stack.peek().label)) {
						
						stack.peek().string = string_stack.peek().string;
						saved_stack.push(stack.pop());
						string_stack.pop();
						 
						
//						for(Item item2:saved_stack) {
//							System.out.print(item2.string+" ");
//							textArea2.append(item2.string+" ");
//						}
//						read_stack(stack);
//						textArea2.append("\n");
						 
 
						System.out.println("");
						 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						continue;
					}
					List<String> nnList = new ArrayList<>();
					if(string_stack.peek().label == "id"|| string_stack.peek().label == "num") {
							nnList = a_lone_line(stack.peek().string, string_stack.peek().label);
							
					}else {
						  nnList = a_lone_line(stack.peek().string, string_stack.peek().string);
					}
					
					 if(nnList == null) {
						System.out.print(stack.peek().string+" "+string_stack.peek().string+" "+string_stack.peek().label);
						System.err.println("出现错误");
						break;
					 }else {
						 stack.pop();
						 Collections.reverse(nnList);
						 for(String string: nnList) {
							 Item item2 = new Item();
							 item2.string = string;
							 stack.push(item2);
						 }
						 
					}
					 
					 
					 for(Item item2:saved_stack) {
							System.out.print(item2.string+" ");
							textArea2.append(item2.string+" ");
						}
					read_stack(stack);
					textArea2.append("\n");
						System.out.println("");
					 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
				
				 
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		   
}
