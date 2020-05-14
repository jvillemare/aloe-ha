package udel.GardenProject.enums;

import javafx.scene.paint.Color;

/**
 * This is one of the stupidest things I've ever done in Java. There is no 
 * built-in enum for Color. Java has a Color class which has zero helper methods
 * for:
 * <ol>
 * <li>Getting all the colors, unlike an enum's <code>values()</code>.</li>
 * <li>Converting those colors to helpful strings for checkbox lists, and</li>
 * <li>Convert strings of color names back into a Color object/enum.</li>
 * </ol>
 * 
 * Color has a <code>web()</code> method that converts a CSS color into a Color
 * object, but it's bad. CSS can specify a color such as 
 * <code>color: red;</code>, but JavaFX's Color class can't handle that complex
 * problem, apparently. Even if it did work (like it's supposed to), it doesn't
 * acheive the other two requirements we have above.<br><br>
 * 
 * May hardship rain upon Oracle for making me do this.
 * 
 * @author Team 0
 */
public enum Colors {
	
	ANYCOLOR(null, "Any Color"),
	ALICEBLUE(Color.ALICEBLUE, "Alice Blue"),
	ANTIQUEWHITE(Color.ANTIQUEWHITE, "Antique White"),
	AQUA(Color.AQUA, "Aqua"),
	AQUAMARINE(Color.AQUAMARINE, "Aquamarine"),
	AZURE(Color.AZURE, "Azure"),
	BEIGE(Color.BEIGE, "Beige"),
	BISQUE(Color.BISQUE, "Bisque"),
	BLACK(Color.BLACK, "Black"),
	BLANCHEDALMOND(Color.BLANCHEDALMOND, "Blanched Almond"),
	BLUE(Color.BLUE, "Blue"),
	BLUEVIOLET(Color.BLUEVIOLET, "Blue Violet"),
	BROWN(Color.BROWN, "Brown"),
	BURLYWOOD(Color.BURLYWOOD, "Burlywood"),
	CADETBLUE(Color.CADETBLUE, "Cadet Blue"),
	CHARTREUSE(Color.CHARTREUSE, "Chartreuse"),
	CHOCOLATE(Color.CHOCOLATE, "Chocolate"),
	CORAL(Color.CORAL, "Coral"),
	CORNFLOWERBLUE(Color.CORNFLOWERBLUE, "Cornflower Blue"),
	CORNSILK(Color.CORNSILK, "Cornsilk"),
	CRIMSON(Color.CRIMSON, "Crimson"),
	CYAN(Color.CYAN, "Cyan"),
	DARKBLUE(Color.DARKBLUE, "Dark Blue"),
	DARKCYAN(Color.DARKCYAN, "Dark Cyan"),
	DARKGOLDENROD(Color.DARKGOLDENROD, "Dark Goldenrod"),
	DARKGRAY(Color.DARKGRAY, "Dark Gray"),
	DARKGREEN(Color.DARKGREEN, "Dark Green"),
	DARKGREY(Color.DARKGREY, "Dark Grey"),
	DARKKHAKI(Color.DARKKHAKI, "Dark Khaki"),
	DARKMAGENTA(Color.DARKMAGENTA, "Dark Magenta"),
	DARKOLIVEGREEN(Color.DARKOLIVEGREEN, "Dark Olive Green"),
	DARKORANGE(Color.DARKORANGE, "Dark Orange"),
	DARKORCHID(Color.DARKORCHID, "Dark Orchid"),
	DARKRED(Color.DARKRED, "Dark Red"),
	DARKSALMON(Color.DARKSALMON, "Dark Salmon"),
	DARKSEAGREEN(Color.DARKSEAGREEN, "Dark Seagreen"),
	DARKSLATEBLUE(Color.DARKSLATEBLUE, "Dark Slate Blue"),
	DARKSLATEGRAY(Color.DARKSLATEGRAY, "Dark Slate Gray"),
	DARKSLATEGREY(Color.DARKSLATEGREY, "Dark Slate Grey"),
	DARKTURQUOISE(Color.DARKTURQUOISE, "Dark Turquoise"),
	DARKVIOLET(Color.DARKVIOLET, "Dark Violet"),
	DEEPPINK(Color.DEEPPINK, "Deep Pink"),
	DEEPSKYBLUE(Color.DEEPSKYBLUE, "Deep Skyblue"),
	DIMGRAY(Color.DIMGRAY, "Dim Gray"),
	DIMGREY(Color.DIMGREY, "Dim Grey"),
	DODGERBLUE(Color.DODGERBLUE, "Doger Blue"),
	FIREBRICK(Color.FIREBRICK, "Firebrick"),
	FLORALWHITE(Color.FLORALWHITE, "Floral White"),
	FORESTGREEN(Color.FORESTGREEN, "Forest Green"),
	FUCHSIA(Color.FUCHSIA, "Fuchsia"),
	GAINSBORO(Color.GAINSBORO, "Gainsboro"),
	GHOSTWHITE(Color.GHOSTWHITE, "Ghost White"),
	GOLD(Color.GOLD, "Gold"),
	GOLDENROD(Color.GOLDENROD, "Goldrod"),
	GRAY(Color.GRAY, "Gray"),
	GREEN(Color.GREEN, "Green"),
	GREENYELLOW(Color.GREENYELLOW, "Green Yellow"),
	GREY(Color.GREY, "Grey"),
	HONEYDEW(Color.HONEYDEW, "Honeydew"),
	HOTPINK(Color.HOTPINK, "Hot Pink"),
	INDIANRED(Color.INDIANRED, "Indian Red"),
	INDIGO(Color.INDIGO, "Indigo"),
	IVORY(Color.IVORY, "Ivory"),
	KHAKI(Color.KHAKI, "Khaki"),
	LAVENDER(Color.LAVENDER, "Lavender"),
	LAVENDERBLUSH(Color.LAVENDERBLUSH, "Lavender Blush"),
	LAWNGREEN(Color.LAWNGREEN, "Lawn Green"),
	LEMONCHIFFON(Color.LEMONCHIFFON, "Lemon Chiffon"),
	LIGHTBLUE(Color.LIGHTBLUE, "Light Blue"),
	LIGHTCORAL(Color.LIGHTCORAL, "Light Coral"),
	LIGHTCYAN(Color.LIGHTCYAN, "Light Cyan"),
	LIGHTGOLDENRODYELLOW(Color.LIGHTGOLDENRODYELLOW, "Light Goldenrod Yellow"),
	LIGHTGRAY(Color.LIGHTGRAY, "Light Gray"),
	LIGHTGREEN(Color.LIGHTGREEN, "Light Green"),
	LIGHTGREY(Color.LIGHTGREY, "Light Grey"),
	LIGHTPINK(Color.LIGHTPINK, "Light Pink"),
	LIGHTSALMON(Color.LIGHTSALMON, "Light Salmon"),
	LIGHTSEAGREEN(Color.LIGHTSEAGREEN, "Light Seagreen"),
	LIGHTSKYBLUE(Color.LIGHTSKYBLUE, "Light Skyblue"),
	LIGHTSLATEGRAY(Color.LIGHTSLATEGRAY, "Light Slate Gray"),
	LIGHTSLATEGREY(Color.LIGHTSLATEGREY, "Light Slate Grey"),
	LIGHTSTEELBLUE(Color.LIGHTSTEELBLUE, "Light Steel Blue"),
	LIGHTYELLOW(Color.LIGHTYELLOW, "Light Yellow"),
	LIME(Color.LIME, "Lime"),
	LIMEGREEN(Color.LIMEGREEN, "Lime Green"),
	LINEN(Color.LINEN, "Linen"),
	MAGENTA(Color.MAGENTA, "Magenta"),
	MAROON(Color.MAROON, "Maroon"),
	MEDIUMAQUAMARINE(Color.MEDIUMAQUAMARINE, "Medium Aquamarine"),
	MEDIUMBLUE(Color.MEDIUMBLUE, "Medium Blue"),
	MEDIUMORCHID(Color.MEDIUMORCHID, "Medium Orchid"),
	MEDIUMPURPLE(Color.MEDIUMPURPLE, "Medium Purple"),
	MEDIUMSEAGREEN(Color.MEDIUMSEAGREEN, "Medium Seagreen"),
	MEDIUMSLATEBLUE(Color.MEDIUMSLATEBLUE, "Medium Slate Blue"),
	MEDIUMSPRINGGREEN(Color.MEDIUMSPRINGGREEN, "Medium Spring Green"),
	MEDIUMTURQUOISE(Color.MEDIUMTURQUOISE, "Medium Turquoise"),
	MEDIUMVIOLETRED(Color.MEDIUMVIOLETRED, "Medium Violetred"),
	MIDNIGHTBLUE(Color.MIDNIGHTBLUE, "Midnight Blue"),
	MINTCREAM(Color.MINTCREAM, "Mint Cream"),
	MISTYROSE(Color.MISTYROSE, "Misty Rose"),
	MOCCASIN(Color.MOCCASIN, "Moccasin"),
	NAVAJOWHITE(Color.NAVAJOWHITE, "Navajo White"),
	NAVY(Color.NAVY, "Navy"),
	OLDLACE(Color.OLDLACE, "Oldlace"),
	OLIVE(Color.OLIVE, "Olive"),
	OLIVEDRAB(Color.OLIVEDRAB, "Olivedrab"),
	ORANGE(Color.ORANGE, "Orange"),
	ORANGERED(Color.ORANGERED, "Orange Red"),
	ORCHID(Color.ORCHID, "Orchid"),
	PALEGOLDENROD(Color.PALEGOLDENROD, "Pale Goldrod"),
	PALEGREEN(Color.PALEGREEN, "Pale Green"),
	PALETURQUOISE(Color.PALETURQUOISE, "Pale Turquoise"),
	PALEVIOLETRED(Color.PALEVIOLETRED, "Pale Violet Red"),
	PAPAYAWHIP(Color.PAPAYAWHIP, "Papaya Whip"),
	PEACHPUFF(Color.PEACHPUFF, "Peach Puff"),
	PERU(Color.PERU, "Peru"),
	PINK(Color.PINK, "Pink"),
	PLUM(Color.PLUM, "Plum"),
	POWDERBLUE(Color.POWDERBLUE, "Power Blue"),
	PURPLE(Color.PURPLE, "Purple"),
	RED(Color.RED, "Red"),
	ROSYBROWN(Color.ROSYBROWN, "Rosy Brown"),
	ROYALBLUE(Color.ROYALBLUE, "Royal Blue"),
	SADDLEBROWN(Color.SADDLEBROWN, "Saddle Brown"),
	SALMON(Color.SALMON, "Salmon"),
	SANDYBROWN(Color.SANDYBROWN, "Sandy Brown"),
	SEAGREEN(Color.SEAGREEN, "Seagreen"),
	SEASHELL(Color.SEASHELL, "Seashell"),
	SIENNA(Color.SIENNA, "Sienna"),
	SILVER(Color.SILVER, "Silver"),
	SKYBLUE(Color.SKYBLUE, "Skyblue"),
	SLATEBLUE(Color.SLATEBLUE, "Slate Blue"),
	SLATEGRAY(Color.SLATEGRAY, "Slate Gray"),
	SLATEGREY(Color.SLATEGREY, "Slate Grey"),
	SNOW(Color.SNOW, "Snow"),
	SPRINGGREEN(Color.SPRINGGREEN, "Spring Green"),
	STEELBLUE(Color.STEELBLUE, "Steel Blue"),
	TAN(Color.TAN, "Tan"),
	TEAL(Color.TEAL, "Teal"),
	THISTLE(Color.THISTLE, "Thistle"),
	TOMATO(Color.TOMATO, "Tomato"),
	TRANSPARENT(Color.TRANSPARENT, "Transparent"),
	TURQUOISE(Color.TURQUOISE, "Turquoise"),
	VIOLET(Color.VIOLET, "Violet"),
	WHEAT(Color.WHEAT, "Wheat"),
	WHITE(Color.WHITE, "White"),
	WHITESMOKE(Color.WHITESMOKE, "White Smoke"),
	YELLOW(Color.YELLOW, "Yellow"),
	YELLOWGREEN(Color.YELLOWGREEN, "Yellow Green");
	
	/**
	 * The actual Color via Java.paint.color.
	 */
	private Color c;
	
	/**
	 * The name of the color in human non all cap.
	 */
	private String name;
	
	private Colors(Color c, String name) {
		this.c = c;
		this.name = name;
	}
	
	/**
	 * Getter for Color
	 * @return Color of c
	 */
	public Color getColor() {
		return this.c;
	}
	
	/**
	 * Getter for name.
	 * @return human name
	 */
	public String getFriendlyName() {
		return name;
	}

}