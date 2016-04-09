package src;

public class hashtags {

   public hashtags() { 
    count = 1; 
    text="";
    }
      public String text ;
      public String indices ;
      public int count ;

      public int lins ;

 /**
  * @return the text
  */
 public String getText() {
  return text;
 }

 /**
  * @param text the text to set
  */
 public void setText(String text) {
  this.text = text;
 }

 /**
  * @return the indices
  */
 public String getIndices() {
  return indices;
 }

 /**
  * @param indices the indices to set
  */
 public void setIndices(String indices) {
  this.indices = indices;
 }

 /**
  * @return the count
  */
 public int getCount() {
  return count;
 }

 /**
  * @param count the count to set
  */
 public void setCount(int count) {
  this.count = count;
 }

 /**
  * @return the lins
  */
 public int getLins() {
  return lins;
 }

 /**
  * @param lins the lins to set
  */
 public void setLins(int lins) {
  this.lins = lins;
 }
}
