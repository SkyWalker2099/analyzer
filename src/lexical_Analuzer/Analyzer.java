package lexical_Analuzer;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Target;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.StyledEditorKit.ForegroundAction;


public class Analyzer {		
	
	static List<Character> digit = Arrays.asList('1','2','3','4','5','6','7','8','9');
   static String letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	static List<Character> letters = new ArrayList<>();
	static List<Character> operater = Arrays.asList('*','/','%','+','-','=','>','<','!','&','|','?');
	static List<Character> delimiter = Arrays.asList(':',';',',');
	static List<Character> xx = Arrays.asList('[',']','{','}','(',')');
	static List<String> reserved_word = Arrays.asList("cout","cin","int","double","float","endl","for","switch","while","string","void");
	static List<String> operators = Arrays.asList("=","!=","==",">>","<<","++","--","+=","-=","%=","/=","*=","+","-","*","/","%","&&","||","?");
	
	static JFrame frame = null;
	static JPanel panel = new JPanel();
	static JTextArea textArea1 = new JTextArea(45,45);
	static JTextArea textArea2 = new JTextArea(45,45);
	static JButton button1 = new JButton("开始词法分析");
	
   public static void main(String[] args) {
   	
   	
   	
   	for ( Character i :  letter.toCharArray()) {
    		letters.add(i);
    	}
   	
   	
   	frame = new JFrame("词法分析器");
   	frame.setSize(1200, 900);
   	
//   	textArea1.setBounds(10,10,780,430);
//   	textArea2.setBounds(810,10,780,430);
   	
   	
//   	sp1.setVerticalScrollBarPolicy(JScrollBar.VERTICAL);
//   	sp2.setVerticalScrollBarPolicy(JScrollBar.VERTICAL);
//   	JScrollPane sp1 = new JScrollPane(textArea1, JScrollBar.VERTICAL, JScrollBar.HORIZONTAL);
//   	JScrollPane sp2 = new JScrollPane(textArea2, JScrollBar.VERTICAL, JScrollBar.HORIZONTAL);
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
			}
		});
   
   	
   	
   	 
    	 
	}
   
   public  static void analyse(String code) {
   	
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
   		System.out.println(i+" "+state+" "+t+" "+s);
   		if(s == -1) {
   			switch (state) {
				case 1:
					System.out.println(toStr(tar) + "\t\t\t十进制整数");
					textArea2.append(toStr(tar) + "\t\t\t十进制整数\n");
					break;
				case 2:
					System.out.println(toStr(tar) +  "\t\t\t十进制整数零");
					textArea2.append(toStr(tar) +  "\t\t\t十进制整数零\n");
					break;
				case 4:
					System.out.println(toStr(tar)+"\t\t\t十进制小数");
					textArea2.append(toStr(tar)+"\t\t\t十进制小数\n");
					break;
				case 3:
					System.out.println(toStr(tar)+"\t\t\t八进制整数");
					textArea2.append(toStr(tar)+"\t\t\t八进制整数\n");
					break;
				case 7:
					System.out.println(toStr(tar)+"\t\t\t十六进制");
					textArea2.append(toStr(tar)+"\t\t\t十六进制\n");
					break;
				case 8:
					String string = toStr(tar);
					if(reserved_word.contains(string)) {
						System.out.println(string+"\t\t\t预留字段");
						textArea2.append(string+"\t\t\t预留字段\n");
					}else {
						System.out.println(string+"\t\t\t标识符");
						textArea2.append(string+"\t\t\t标识符\n");
					}
					
					break;
				case 9:
					String string2 = toStr(tar);
					try {
						if(operators.contains(string2)) {
						System.out.println(string2+"\t\t\t运算符");
						textArea2.append(string2+"\t\t\t运算符\n");
					}else if("//".equals(string2.substring(0, 2))) {
						flag = true;
					}
					else {
						System.out.println(string2+"\t\t\t此处出错");
						textArea2.append(string2+"\t\t\t此处出错"+"位于第"+line_num+"行\n");
					}
					} catch (Exception e) {
						System.out.println(line_num+"\n");
						e.printStackTrace();
						// TODO: handle exception
					}
					break;
				case 10:
					
					System.out.println(toStr(tar)+"\t\t\t分隔符");
					textArea2.append(toStr(tar)+"\t\t\t分隔符\n");
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
   	}
   }
   
   public static String toStr(List<Character> list) {
   	String str = "";
   	for(char i : list) {
   		str = str + i;
   	}
   	return str;
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
   
   
   
}































