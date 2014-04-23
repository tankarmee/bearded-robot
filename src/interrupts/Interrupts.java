package interrupts;

import video.Printer;

/**
 * Interrupt-Verwatlungsklasse
 */
public class Interrupts {

	/**
	 * Standard-Interrupt-Handler
	 */
	private static class DefaultHandler extends InterruptHandler {

		/**
		 * Hält System an und gibt Interrupt-Informationen aus.
		 *
		 * @param number Nummer des Interrupts
		 * @param errorCode Fehlercode bei Interrupts mit Fehlercode, sonst null.
		 */
		@Override
		public void onInterrupt(int number, Integer errorCode) {
			Printer.directPrintString("Interrupt:", 0, 24, Printer.WHITE, Printer.BLACK);
			Printer.directPrintInt(number, 10, 0, 11, 24, Printer.WHITE, Printer.BLACK);
			while (true);
		}
	}

	/**
	 * Basisadresse der IDT.
	 */
	public static final int IDT_BASE = 0x7E00;

	/**
	 * IDT des Protected Mode.
	 */
	public static final Idt IDT = (Idt)MAGIC.cast2Struct(IDT_BASE);

	/**
	 * Limit der IDT.
	 */
	public static final int IDT_LIMIT = 8 * Idt.SIZE - 1;

	/**
	 * Die Referenzen auf die Interrupt-Handler.
	 */
	public static final InterruptHandler[] HANDLERS = new InterruptHandler[Idt.SIZE];

	private Interrupts() {
	}

	/**
	 * Initialisiert die Interrupt-Funktionalität.
	 */
	public static void init() {

		// Exceptions
		setIsr(0x00, MAGIC.mthdOff("Interrupts", "isr0")); // 0 - Divide-by-zero
		setIsr(0x01, MAGIC.mthdOff("Interrupts", "isr1")); // 1 - Debug exception
		setIsr(0x02, MAGIC.mthdOff("Interrupts", "isr2")); // 2 - Non-Maskable Interrupt (NMI)
		setIsr(0x03, MAGIC.mthdOff("Interrupts", "isr3")); // 3 - Breakpoint (INT 3)
		setIsr(0x04, MAGIC.mthdOff("Interrupts", "isr4")); // 4 - Overflow (INTO)
		setIsr(0x05, MAGIC.mthdOff("Interrupts", "isr5")); // 5 - Bound exception
		setIsr(0x06, MAGIC.mthdOff("Interrupts", "isr6")); // 6 - Invalid Opcode
		setIsr(0x07, MAGIC.mthdOff("Interrupts", "isr7")); // 7 - reserviert
		setIsr(0x08, MAGIC.mthdOff("Interrupts", "isr8")); // 8 - Double Fault
		setIsr(0x09, MAGIC.mthdOff("Interrupts", "isr9")); // 9 - reserviert
		setIsr(0x0A, MAGIC.mthdOff("Interrupts", "isr10")); // 10 - reserviert
		setIsr(0x0B, MAGIC.mthdOff("Interrupts", "isr11")); // 11 - reserviert
		setIsr(0x0C, MAGIC.mthdOff("Interrupts", "isr12")); // 12 - reserviert
		setIsr(0x0D, MAGIC.mthdOff("Interrupts", "isr13")); // 13 - General Protection Error
		setIsr(0x0E, MAGIC.mthdOff("Interrupts", "isr14")); // 14 - Page Fault
		setIsr(0x0F, MAGIC.mthdOff("Interrupts", "isr15")); // 15 - reserviert
		setIsr(0x10, MAGIC.mthdOff("Interrupts", "isr16")); // 16 - reserviert
		setIsr(0x11, MAGIC.mthdOff("Interrupts", "isr17")); // 17 - reserviert
		setIsr(0x12, MAGIC.mthdOff("Interrupts", "isr18")); // 18 - reserviert
		setIsr(0x13, MAGIC.mthdOff("Interrupts", "isr19")); // 19 - reserviert
		setIsr(0x14, MAGIC.mthdOff("Interrupts", "isr20")); // 20 - reserviert
		setIsr(0x15, MAGIC.mthdOff("Interrupts", "isr21")); // 21 - reserviert
		setIsr(0x16, MAGIC.mthdOff("Interrupts", "isr22")); // 22 - reserviert
		setIsr(0x17, MAGIC.mthdOff("Interrupts", "isr23")); // 23 - reserviert
		setIsr(0x18, MAGIC.mthdOff("Interrupts", "isr24")); // 24 - reserviert
		setIsr(0x19, MAGIC.mthdOff("Interrupts", "isr25")); // 25 - reserviert
		setIsr(0x1A, MAGIC.mthdOff("Interrupts", "isr26")); // 26 - reserviert
		setIsr(0x1B, MAGIC.mthdOff("Interrupts", "isr27")); // 27 - reserviert
		setIsr(0x1C, MAGIC.mthdOff("Interrupts", "isr28")); // 28 - reserviert
		setIsr(0x1D, MAGIC.mthdOff("Interrupts", "isr29")); // 29 - reserviert
		setIsr(0x1E, MAGIC.mthdOff("Interrupts", "isr30")); // 30 - reserviert
		setIsr(0x1F, MAGIC.mthdOff("Interrupts", "isr31")); // 31 - reserviert

		// Hardware-Interrupts
		setIsr(0x20, MAGIC.mthdOff("Interrupts", "isr32")); // 32 - timer (IRQ0)
		setIsr(0x21, MAGIC.mthdOff("Interrupts", "isr33")); // 33 - Tastatur (IRQ1)
		setIsr(0x22, MAGIC.mthdOff("Interrupts", "isr34")); // 34 - IRQ2
		setIsr(0x23, MAGIC.mthdOff("Interrupts", "isr35")); // 35 - IRQ3
		setIsr(0x24, MAGIC.mthdOff("Interrupts", "isr36")); // 36 - IRQ4
		setIsr(0x25, MAGIC.mthdOff("Interrupts", "isr37")); // 37 - IRQ5
		setIsr(0x26, MAGIC.mthdOff("Interrupts", "isr38")); // 38 - IRQ6
		setIsr(0x27, MAGIC.mthdOff("Interrupts", "isr39")); // 39 - IRQ7
		setIsr(0x28, MAGIC.mthdOff("Interrupts", "isr40")); // 40 - IRQ8
		setIsr(0x29, MAGIC.mthdOff("Interrupts", "isr41")); // 41 - IRQ9
		setIsr(0x2A, MAGIC.mthdOff("Interrupts", "isr42")); // 42 - IRQ10
		setIsr(0x2B, MAGIC.mthdOff("Interrupts", "isr43")); // 43 - IRQ11
		setIsr(0x2C, MAGIC.mthdOff("Interrupts", "isr44")); // 44 - IRQ12
		setIsr(0x2D, MAGIC.mthdOff("Interrupts", "isr45")); // 45 - IRQ13
		setIsr(0x2E, MAGIC.mthdOff("Interrupts", "isr46")); // 46 - IRQ14
		setIsr(0x2F, MAGIC.mthdOff("Interrupts", "isr47")); // 47 - IRQ15

		// Standardhandler setzen
		InterruptHandler defaultHandler = new DefaultHandler();
		for(int i = 0; i < Idt.SIZE; i++) {
			HANDLERS[i] = defaultHandler;
		}

		// IDT laden
		loadInterruptDescriptorTable(IdtTypes.PROTECTED_MODE);

		// Initialisiere PICs
		Pics.init();
	}

	/**
	 * Initialisiert einen Eintrag der IDT mit dem angegebenen Methoden-Offset.
	 *
	 * @param n Index des IDT-Eintrags.
	 * @param methodOffset Methoden-Offset einer ISR.
	 */
	private static void setIsr(int n, int methodOffset) {
		int codeOffset = MAGIC.getCodeOff();
		int classReference = MAGIC.cast2Ref(MAGIC.clssDesc("Interrupts"));
		int isrAddress = MAGIC.rMem32(classReference + methodOffset) + codeOffset;
		IDT.entries[n].offsetLo = (short)(isrAddress & 0xFFFF);
		IDT.entries[n].selector = 0x8;
		IDT.entries[n].zero = 0;
		IDT.entries[n].flags = (byte)0x8E;
		IDT.entries[n].offsetHi = (short)((isrAddress >> 16) & 0xFFFF);
	}

	/**
	 * Lädt die Interrupt-Descriptor-Tabelle vom angegebenen Typ.
	 *
	 * @see interrupts.IdtTypes
	 *
	 * @param idtType Der Typ der IDT, welche geladen werden soll.
	 */
	public static void loadInterruptDescriptorTable(int idtType) {
		long tmp;

		switch (idtType) {
			case IdtTypes.REAL_MODE:
				tmp = (long)1023;
				break;
			case IdtTypes.PROTECTED_MODE:
				tmp = (((long)IDT_BASE) << 16) | (long)IDT_LIMIT;
				break;
			default:
				return;
		}

		MAGIC.inline(0x0F, 0x01, 0x5D);
		MAGIC.inlineOffset(1, tmp); // lidt [ebp-0x08/tmp]
	}

	/**
	 * Gibt die Interrupts frei.
	 */
	@SJC.Inline
	public static void enableIRQs() {
		MAGIC.inline(0xFB);
	}

	/**
	 * Sperrt die Interrupts.
	 */
	@SJC.Inline
	public static void disableIRQs() {
		MAGIC.inline(0xFA);
	}


	// Es folgen die Interrupt-Service-Routinen, welche jeweils ihren
	// InterruptHandler aufrufen. Bei Hardware-Interrupts erfolgt außerdem eine
	// Interruptbestätigung

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr0() {
		Interrupts.disableIRQs();
		HANDLERS[0].onInterrupt(0, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr1() {
		Interrupts.disableIRQs();
		HANDLERS[1].onInterrupt(1, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr2() {
		Interrupts.disableIRQs();
		HANDLERS[2].onInterrupt(2, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr3() {
		Interrupts.disableIRQs();
		HANDLERS[3].onInterrupt(3, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr4() {
		Interrupts.disableIRQs();
		HANDLERS[4].onInterrupt(4, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr5() {
		Interrupts.disableIRQs();
		HANDLERS[5].onInterrupt(5, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr6() {
		Interrupts.disableIRQs();
		HANDLERS[6].onInterrupt(6, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr7() {
		Interrupts.disableIRQs();
		HANDLERS[7].onInterrupt(7, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr8(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[8].onInterrupt(8, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr9() {
		Interrupts.disableIRQs();
		HANDLERS[9].onInterrupt(9, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr10(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[10].onInterrupt(10, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr11(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[11].onInterrupt(11, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr12(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[12].onInterrupt(12, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr13(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[13].onInterrupt(13, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings({"UnusedDeclaration", "UnnecessaryBoxing"})
	@SJC.Interrupt
	public static void isr14(int arg) {
		Interrupts.disableIRQs();
		HANDLERS[14].onInterrupt(14, new Integer(arg));
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr15() {
		Interrupts.disableIRQs();
		HANDLERS[15].onInterrupt(15, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr16() {
		Interrupts.disableIRQs();
		HANDLERS[16].onInterrupt(16, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr17() {
		Interrupts.disableIRQs();
		HANDLERS[17].onInterrupt(17, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr18() {
		Interrupts.disableIRQs();
		HANDLERS[18].onInterrupt(18, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr19() {
		Interrupts.disableIRQs();
		HANDLERS[19].onInterrupt(19, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr20() {
		Interrupts.disableIRQs();
		HANDLERS[20].onInterrupt(20, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr21() {
		Interrupts.disableIRQs();
		HANDLERS[21].onInterrupt(21, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr22() {
		Interrupts.disableIRQs();
		HANDLERS[22].onInterrupt(22, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr23() {
		Interrupts.disableIRQs();
		HANDLERS[23].onInterrupt(23, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr24() {
		Interrupts.disableIRQs();
		HANDLERS[24].onInterrupt(24, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr25() {
		Interrupts.disableIRQs();
		HANDLERS[25].onInterrupt(25, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr26() {
		Interrupts.disableIRQs();
		HANDLERS[26].onInterrupt(26, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr27() {
		Interrupts.disableIRQs();
		HANDLERS[27].onInterrupt(27, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr28() {
		Interrupts.disableIRQs();
		HANDLERS[28].onInterrupt(28, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr29() {
		Interrupts.disableIRQs();
		HANDLERS[29].onInterrupt(29, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr30() {
		Interrupts.disableIRQs();
		HANDLERS[30].onInterrupt(30, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr31() {
		Interrupts.disableIRQs();
		HANDLERS[31].onInterrupt(31, null);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr32() {
		Interrupts.disableIRQs();
		HANDLERS[32].onInterrupt(32, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr33() {
		Interrupts.disableIRQs();
		HANDLERS[33].onInterrupt(33, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr34() {
		Interrupts.disableIRQs();
		HANDLERS[34].onInterrupt(34, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr35() {
		Interrupts.disableIRQs();
		HANDLERS[35].onInterrupt(35, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr36() {
		Interrupts.disableIRQs();
		HANDLERS[36].onInterrupt(36, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr37() {
		Interrupts.disableIRQs();
		HANDLERS[37].onInterrupt(37, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr38() {
		Interrupts.disableIRQs();
		HANDLERS[38].onInterrupt(38, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr39() {
		Interrupts.disableIRQs();
		HANDLERS[39].onInterrupt(39, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr40() {
		Interrupts.disableIRQs();
		HANDLERS[40].onInterrupt(40, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr41() {
		Interrupts.disableIRQs();
		HANDLERS[41].onInterrupt(41, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr42() {
		Interrupts.disableIRQs();
		HANDLERS[42].onInterrupt(42, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr43() {
		Interrupts.disableIRQs();
		HANDLERS[43].onInterrupt(43, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr44() {
		Interrupts.disableIRQs();
		HANDLERS[44].onInterrupt(44, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr45() {
		Interrupts.disableIRQs();
		HANDLERS[45].onInterrupt(45, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr46() {
		Interrupts.disableIRQs();
		HANDLERS[46].onInterrupt(46, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

	@SuppressWarnings("UnusedDeclaration")
	@SJC.Interrupt
	public static void isr47() {
		Interrupts.disableIRQs();
		HANDLERS[47].onInterrupt(47, null);
		MAGIC.wIOs8(Pics.MASTER, (byte)0x20);
		MAGIC.wIOs8(Pics.SLAVE, (byte)0x20);
		Interrupts.enableIRQs();
	}

}
