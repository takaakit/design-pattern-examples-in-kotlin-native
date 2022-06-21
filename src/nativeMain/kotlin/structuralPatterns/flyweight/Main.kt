package structuralPatterns.flyweight

/*
Display a string consisting of large characters (0-9 digits only).
Large character objects are not created until they are needed.
And the created objects are reused.

Example Output
-----
Please enter digits (ex. 1212123): 123
              
     ####     
      ###     
      ###     
      ###     
      ###     
      ###     
    #######   
              

              
   ########   
         ###  
         ###  
   ########   
  #           
  #           
  ##########  
              

              
   ########   
         ###  
         ###  
   ########   
         ###  
  #      ###  
   ########
 */

fun main() {
    println("Please enter digits (ex. 1212123):")
    val inputValue = readLine()

    inputValue?.also {
        val bs = LargeSizeString(inputValue)
        bs.display()
    }
}
