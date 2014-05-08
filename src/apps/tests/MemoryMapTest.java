package apps.tests;

import bios.BIOS;
import bios.MemoryMapBuffer;
import kernel.Kernel;
import video.Printer;

public class MemoryMapTest {
	public static void printFree() {
		MemoryMapBuffer buffer = (MemoryMapBuffer)MAGIC.cast2Struct(Kernel.MEMORY_MAP_BUFFER_BASE);

		Printer printer = new Printer();
		printer.setCursor(0, 5);
		printer.print("--------------------------------------------------------------------------------");
		printer.println();
		printer.println("Memory map:");
		printer.println("Base               | Length             | Type");

		BIOS.regs.EBX = 0;

		do {
			BIOS.regs.EAX = 0xE820;
			BIOS.regs.EDX = 0x534D4150;
			BIOS.regs.ECX = 20;
			BIOS.regs.ES = 0x0;
			BIOS.regs.EDI = Kernel.MEMORY_MAP_BUFFER_BASE;

			BIOS.rint(0x15);

			if(buffer.base >= 100000 && buffer.type == 1) {
				printer.setColor(Printer.RED, Printer.BLACK);
			}
			printer.printHex(buffer.base);
			printer.print(" | ");
			printer.printHex(buffer.length);
			printer.print(" | ");
			printer.print(buffer.type);
			printer.println();
			printer.setColor(Printer.WHITE, Printer.BLACK);
		} while (BIOS.regs.EBX != 0 && (BIOS.regs.FLAGS & BIOS.F_CARRY) == 0);
	}
}