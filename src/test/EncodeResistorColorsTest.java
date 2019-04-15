import codewars.EncodeResistorColors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncodeResistorColorsTest {
    @Test
    public void SomeCommonResistorValuesTests() {
        assertEquals("brown black black gold", EncodeResistorColors.encodeResistorColors("10 ohms"));
        assertEquals("yellow violet black gold", EncodeResistorColors.encodeResistorColors("47 ohms"));
        assertEquals("brown black brown gold", EncodeResistorColors.encodeResistorColors("100 ohms"));
        assertEquals("red red brown gold", EncodeResistorColors.encodeResistorColors("220 ohms"));
        assertEquals("orange orange brown gold", EncodeResistorColors.encodeResistorColors("330 ohms"));
        assertEquals("yellow violet brown gold", EncodeResistorColors.encodeResistorColors("470 ohms"));
        assertEquals("blue gray brown gold", EncodeResistorColors.encodeResistorColors("680 ohms"));
        assertEquals("brown black red gold", EncodeResistorColors.encodeResistorColors("1k ohms"));
        assertEquals("yellow violet red gold", EncodeResistorColors.encodeResistorColors("4.7k ohms"));
        assertEquals("brown black orange gold", EncodeResistorColors.encodeResistorColors("10k ohms"));
        assertEquals("red red orange gold", EncodeResistorColors.encodeResistorColors("22k ohms"));
        assertEquals("yellow violet orange gold", EncodeResistorColors.encodeResistorColors("47k ohms"));
        assertEquals("brown black yellow gold", EncodeResistorColors.encodeResistorColors("100k ohms"));
        assertEquals("orange orange yellow gold", EncodeResistorColors.encodeResistorColors("330k ohms"));
        assertEquals("brown black green gold", EncodeResistorColors.encodeResistorColors("1M ohms"));
        assertEquals("red black green gold", EncodeResistorColors.encodeResistorColors("2M ohms"));
    }
}
