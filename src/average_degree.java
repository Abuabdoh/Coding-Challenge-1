package src;
import java.awt.List;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
 import java.math.BigDecimal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class average_degree {
 
  private static  ArrayList<Tweet> tweetLst= new ArrayList<Tweet>();
  public static double Avg = 0.00f;

     public static String Result = "";
   @SuppressWarnings("deprecation")
 private static void CalcAvarageTweets(Tweet t)
      {
          try
          {
           Collections.sort(tweetLst, new Comparator<Tweet>() {
                 @Override
                 public int compare(Tweet Tweet2, Tweet Tweet1)
                 {

                     return  Tweet1.created_at.compareTo(Tweet2.created_at);
                 }
             });
             

              Tweet twt = tweetLst.get(0);
              
             
              Calendar cal = Calendar.getInstance();
              cal.setTime(twt.getRealDateTime());
              cal.add(Calendar.MINUTE, -1);
              Date oneMinBack = cal.getTime();
              
                Date MaxMin60 = twt.getRealDateTime();
                
              ArrayList<Tweet> last60Tweets = new ArrayList<Tweet>(); //SortedList.Where(o => o.RealDateTime <= twt.RealDateTime && o.RealDateTime >= MaxMin60).ToList();
for(int x=0;x<tweetLst.size();x++){
 if(twt.getRealDateTime().compareTo(tweetLst.get(x).getRealDateTime()) >=0 && tweetLst.get(x).getRealDateTime().compareTo(oneMinBack) >=0)
  last60Tweets.add(tweetLst.get(x));
}
              double Avg = 0.0f;

              ArrayList<hashtags> hashLst = new ArrayList<hashtags>();

              for(int y=0;y<last60Tweets.size();y++)
              {
                last60Tweets.get(y).DoublecateHT=0;
                  for(int z=0;z<last60Tweets.get(y).getHashtags().size();z++ )
                  {
                      boolean IsFound = false;
                      
                      for(int n=0;n<hashLst.size();n++)
                      {
                          if (hashLst.get(n).text.toString().equals(last60Tweets.get(y).getHashtags().get(z).text.toString()))
                          {
                              IsFound = true;
                              last60Tweets.get(y).DoublecateHT++;
                              break;
                          }
                      }

                      if (!IsFound && last60Tweets.get(y).hashtags.size() > 1)
                          hashLst.add(last60Tweets.get(y).getHashtags().get(z));
                  }
              }

              double BigCount = 0;
              double BigSum = hashLst.size();


              for(int y=0;y<last60Tweets.size();y++)
              {
               Tweet tt= last60Tweets.get(y);
                  BigCount = BigCount + (tt.hashtags.size() * (tt.hashtags.size() - 1));
                  if(last60Tweets.get(y).DoublecateHT >0)
              {
               BigCount = BigCount - (last60Tweets.get(y).DoublecateHT -1) * last60Tweets.get(y).DoublecateHT;
              }
                  
              }
              
              if(BigSum != 0)
              Avg = BigCount / BigSum;
              else
              Avg=0;
              twt.Avg = Avg;

              String strAvg = "";
             strAvg =truncateDecimal(twt.Avg,2).toString();


             Result= Result+ strAvg + "\r\n";


          }
          catch (Exception ex)
          {
              ex.printStackTrace();
          }
      }
   
   
     private static BigDecimal truncateDecimal(double x,int numberofDecimals)
   {
       if ( x > 0) {
           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
       } else {
           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
       }
   }
   
   
 public static void writeStringtoFile(String path, String result){
     FileOutputStream fop = null;
     try {
    
   File file;
   String content = result;
      file = new File(path); 
    fop = new FileOutputStream(file);

    // if file doesnt exists, then create it
    if (!file.exists()) {
      System.out.println("Error");
    }

    // get the content in bytes
    byte[] contentInBytes = content.getBytes();

    fop.write(contentInBytes);
    fop.flush();
    fop.close();

     } catch (IOException e) {
    e.printStackTrace();
   } finally {
    try {
     if (fop != null) {
      fop.close();
     }
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
   
   }

  public static void main(String[] args)
  {

   JSONParser parser = new JSONParser();
   String IFile = "./tweet_input/tweets.txt";
   String OFile = "./tweet_output/output.txt";
   if(args.length != 0)
   {
    IFile= args[0].toString();
    OFile= args[1].toString();
   }
        
  
          try(BufferedReader br = new BufferedReader(new FileReader(IFile))) {
              for(String line; (line = br.readLine()) != null; ) {
               try{
               Object obj =parser.parse(line);
               
                JSONObject jsonObject = (JSONObject) obj;
                if(jsonObject.containsKey("limit")){
                    continue;
                }
                 
                   Tweet t = new Tweet();
                   t.setCreated_at(convertDate((String)jsonObject.get("created_at")));
                   t.setId_str((String) jsonObject.get("id_str"));
                   
                   JSONObject entities = (JSONObject) jsonObject.get("entities");
                   JSONArray hashtags=(JSONArray)entities.get("hashtags");
                   t.hashtags = new ArrayList<hashtags>();
                  readHashTags(hashtags, t);
                  t.setRealDateTime(t.getCreated_at());
                 if (t.getId_str() != null)
                   {
                       t.Num = tweetLst.size() + 1;
                       tweetLst.add(t);
                       CalcAvarageTweets(t);
                   }
                 
               
               }
              catch(Exception e){
              e.printStackTrace(); 
              }
              
              
               
                 writeStringtoFile(OFile,Result);
                 
                  
              }
              // line is not visible here.
          } catch (FileNotFoundException e1) {
     
     e1.printStackTrace();
    } catch (IOException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
          
 } 
  
 
  public static void readHashTags(JSONArray sp_arr, Tweet t){
   for (int i = 0; i < sp_arr.size(); i++) {
          hashtags ht = new hashtags();
            JSONObject jsonobject = (JSONObject)sp_arr.get(i);
            ht.setText((String) jsonobject.get("text"));
            
   boolean isFound = false;
   hashtags th=new hashtags();
   for(int b = 0; b < t.getHashtags().size(); b++)
   th = t.getHashtags().get(b);
             if (th.getText().toString().equals(ht.getText().toString()))
             {
                 isFound = true;
                 
             }

         if (!isFound)
             t.getHashtags().add(ht);
         
   }
  }
  public static Date convertDate(String creatDdate){
   
   try {
    Date CreatDdate=null;
   String dateStr = creatDdate;
   SimpleDateFormat format =new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
   SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
   SimpleDateFormat a = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.getDefault());
  
   CreatDdate = (Date)a.parse(dateStr);
   return CreatDdate;
  } catch (ParseException e) {
   // TODO Auto-generated catch block
    return null;
   //e.printStackTrace();
  }
  
  } 
}

   

