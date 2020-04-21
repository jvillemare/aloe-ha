Ripped from Canvas

---

Several people asked me today to repeat info on what needs to be tested. Here is text:

What needs to be tested:

1) testing everything is your goal!

2) To test an event handler, make a new event and pass it in. [You can simulate a mouse drag by making an event, then pass it as parameter to your handler](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/MouseEvent.html#MouseEvent-java.lang.Object-javafx.event.EventTarget-javafx.event.EventType-double-double-double-double-javafx.scene.input.MouseButton-int-boolean-boolean-boolean-boolean-boolean-boolean-boolean-boolean-boolean-boolean-javafx.scene.input.PickResult-)

3) There is a Robot class for GUI testing. You are not required to use it but it is cool.

4) Sometimes it is easier to write a regular method to do the work (e.g. calculating coordinates, changing state), test that method, and have the handler call the method. In this case I will not require you to test the handler.

5) Only the Model is required to be 100 percent tested. (Use EclEmma or other covereage tool to determine coverage.) In the View and Controller you are required to test things that have return values and/or side effects that affect state (e.g. a void method that moves a Plant results in the Plant having new coordinates.)
