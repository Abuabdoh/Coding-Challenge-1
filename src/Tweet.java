package src;
import java.util.Date;
import java.util.List;

import javax.print.attribute.DateTimeSyntax;


public class Tweet {

 public String id_str =null; 
    public double Value ; 
    public List<hashtags> hashtags;  
    public Date created_at ; 
    public Date RealDateTime ;
    public boolean Positive ; 
    public int Num ; 
    public double Avg ; 
    public int DoublecateHT ; 
    
    public Tweet() {

    }

    public Tweet(String _Id, Date _CreationTime, List<hashtags> _HashTags)
    {
        this.id_str = _Id;
        this.created_at = _CreationTime;
        this.hashtags = _HashTags;

        this.Value = (hashtags.size() - 1) * hashtags.size();
    }

 /**
  * @return the id_str
  */
 public String getId_str() {
  return id_str;
 }

 /**
  * @param id_str the id_str to set
  */
 public void setId_str(String id_str) {
  this.id_str = id_str;
 }

 /**
  * @return the value
  */
 public double getValue() {
  return Value;
 }

 /**
  * @param value the value to set
  */
 public void setValue(double value) {
  Value = value;
 }

 /**
  * @return the hashtags
  */
 public List<hashtags> getHashtags() {
  return hashtags;
 }

 /**
  * @param hashtags the hashtags to set
  */
 public void setHashtags(List<hashtags> hashtags) {
  this.hashtags = hashtags;
 }

 /**
  * @return the created_at
  */
 public Date getCreated_at() {
  return created_at;
 }

 /**
  * @param created_at the created_at to set
  */
 public void setCreated_at(Date created_at) {
  this.created_at = created_at;
 }

 /**
  * @return the realDateTime
  */
 public Date getRealDateTime() {
  return RealDateTime;
 }

 /**
  * @param realDateTime the realDateTime to set
  */
 public void setRealDateTime(Date realDateTime) {
  RealDateTime = realDateTime;
 }

 /**
  * @return the positive
  */
 public boolean isPositive() {
  return Positive;
 }

 /**
  * @param positive the positive to set
  */
 public void setPositive(boolean positive) {
  Positive = positive;
 }

 /**
  * @return the num
  */
 public int getNum() {
  return Num;
 }

 /**
  * @param num the num to set
  */
 public void setNum(int num) {
  Num = num;
 }


 /**
  * @return the avg
  */
 public double getAvg() {
  return Avg;
 }

 /**
  * @param avg the avg to set
  */
 public void setAvg(double avg) {
  Avg = avg;
 }

 /**
  * @return the doublecateHT
  */
 public int getDoublecateHT() {
  return DoublecateHT;
 }

 /**
  * @param doublecateHT the doublecateHT to set
  */
 public void setDoublecateHT(int doublecateHT) {
  this.DoublecateHT = doublecateHT;
 }

}
