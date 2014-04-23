package keyboard;

import interrupts.InterruptHandler;

class KeyboardInterruptHandler extends InterruptHandler {

	private int remaining;

	private int scanCodeBuffer;

	private int breakpointBuffer;

	@Override
	public void onInterrupt(int number, Integer errorCode) {
		int b = MAGIC.rIOs8(0x60);
		if(b < 0) { // undo signed conversion
			b += 256;
			b |= 0x80;
		}

		breakpointBuffer = ((breakpointBuffer << 8) | b) & 0xFFFFFF;
		if(breakpointBuffer == 0x1D2A01 || breakpointBuffer == 0x2A1D01) {
			MAGIC.inline(0xCC);
		}

		if(remaining > 0) {
			scanCodeBuffer = (scanCodeBuffer << 8) | b;
			remaining--;
		} else {
			if(b >= 0xE2) return;
			scanCodeBuffer = b;
			remaining = b - 0xDF;
		}

		if(remaining <= 0) {
			Keyboard.buffer.push(scanCodeBuffer);
		}
	}
}
