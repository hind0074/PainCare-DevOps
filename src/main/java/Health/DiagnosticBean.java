package Health;

import java.sql.Date;
import java.util.ArrayList;

import Health.servlets.Diagnostic;

public class DiagnosticBean {
	private int id;
	private int user_id;
	private ArrayList<ArrayList<Integer>> answers;
	private Date date;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUserID(int id) {
		this.user_id = id;
	}
	
	public void setAnswers(String answers) {
		ArrayList<ArrayList<Integer>> intList = new ArrayList<ArrayList<Integer>>();
        String[] parts = answers.split(",");

        for (String part : parts) {
        	ArrayList<Integer> valuesList = new ArrayList<Integer>();
        	String[] values = part.split("-");
        	
        	for (String value : values) valuesList.add(Integer.parseInt(value));
        	
        	intList.add(valuesList);
        }
        
        this.answers = intList;
	}
	
	public int geID() {
		return this.id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public ArrayList<ArrayList<Integer>> getAnswers() {
		return this.answers;
	}
	
	private float answerValue(int question, ArrayList<Integer> answer) {
		switch(question) {
	    	case 0 : switch(answer.get(0)) {
	    		case 0: return (float) 3;
	    		case 1: return (float) 0;
			}; break;
			
	    	case 1 : switch(answer.get(0)) {
	    		case 0: return (float) 3;
	    		case 1: return (float) 0;
	    		case 2: return (float) 0;
			}; break;
			
	    	case 2 : switch(answer.get(0)) {
	    		case 0: return (float) 3;
	    		case 1: return (float) 0;
			}; break;
			
	    	case 3 : switch(answer.get(0)) {
	    		case 0: return (float) 0;
	    		case 1: return (float) 3;
			}; break;
			
	    	case 4 : switch(answer.get(0)) {
	    		case 0: return (float) 3;
	    		case 1: return (float) 0;
			}; break;
			
	    	case 5 : {
	    		int weight = answer.get(0);
	    		int height = answer.get(1);
	    		float bmi = (float) 10000 * (weight) / (height * height);
	    		
	    		return bmi > 18.5 ? 1 : 0;
	    	}
	    	case 6 : switch(answer.get(0)) {
	    		case 0: return (float) 0;
	    		case 1: return (float) 1;
	    		case 2: return (float) 2;
	    		case 3: return (float) 3;
			};
	    	case 7 : {
	    		float val = 0;
	    		
	    		for(int i = 0; i < answer.size(); i++) {
	    			switch(answer.get(i)) {
	    				case 0: val += 1; break;
	    				case 1: val += 1; break;
	    				case 2: val += 0; break;
	    			}
	    		}
	    		
	    		return val;
	    	}
	    	case 8 : switch(answer.get(0)) {
	    		case 0: return (float) 0;
	    		case 1: return (float) 1;
	    		case 2: return (float) 2;
	    		case 3: return (float) 3;
			};
	    	case 9 : {
	    		float val = 0;
	    		
	    		for(int i = 0; i < answer.size(); i++) {
	    			switch(answer.get(i)) {
	    				case 0: val += 1; break;
	    				case 1: val += 1; break;
	    				case 2: val += 1; break;
	    				case 3: val += 1; break;
	    				case 4: val += 0; break;
	    			}
	    		}
	    		
	    		return val;
	    	}
	    	case 10 : switch(answer.get(0)) {
	    		case 0: return (float) 0;
	    		case 1: return (float) 3;
			};
	    	case 11 : switch(answer.get(0)) {
	    		case 0: return (float) 3;
	    		case 1: return (float) 1;
	    		case 2: return (float) 0;
			};
	    	case 12 : switch(answer.get(0)) {
	    		case 0: return (float) 0;
	    		case 1: return (float) 1;
	    		case 2: return (float) 1;
			};
	    	case 13 : {
	    		float val = 0;
	    		
	    		for(int i = 0; i < answer.size(); i++) {
	    			switch(answer.get(i)) {
	    				case 0: val += 1; break;
	    				case 1: val += 1; break;
	    				case 2: val += 1; break;
	    				case 3: val += 1; break;
	    			}
	    		}
	    		
	    		return val;
	    	}
	    	case 14 : switch(answer.get(0)) {
	    		case 0: return (float) 1;
	    		case 1: return (float) 0;
			}
	    }
		
		return (float) 0;
	}
	
	public float calcScore() {
		float result = 0;
		int factorsSum = 0;
		
		for (int question = 0; question < answers.size(); question++) {
			int factor = (int) Diagnostic.questionsBank[question][3];
			ArrayList<Integer> answer = answers.get(question);
			
			factorsSum += factor;
            result += answerValue(question, answer) * factor;
        }
		
		return 100 * result / 41;
	}
	
	public String calcResult() {
		float res = calcScore() * 41 / 100;

		if(res > 25) return "High";
		if(res > 12) return "Medium";
		
		return "Low";
	}
}
