/**
 * Contains all the plot objects that can be placed on the Plot Designer.<br>
 * <br>
 * 
 * Placed within the <code>main.udel.plants</code> package as plants can also
 * be placed on the Plot Designer.<br><br>
 * 
 * Has Buildings, Fences, Rivers, and other critical plot objects.<br><br>
 * 
 * NOTE: Everything in this package needs to implement the Serializable 
 * interface to be serialized by Model's save method when acting on the Session
 * class.<br><br>
 * 
 * NOTE 2: Every class in this package and any subpackages must implement
 * <code>PlotObject</code> or implement an abstract class that itself 
 * implements PlotObject at some point.
 * 
 * @author Team 0
 */
package udel.GardenProject.plotObjects;