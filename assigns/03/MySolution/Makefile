########################################################################
#
# A simple Makefile for compiling Java code for CS392X1
#
########################################################################
JAVA=java
JAVAC=javac
########################################################################
MYCPS=.:./../../../assets/algs4/algs4.jar
########################################################################
all:: \
    Assign03_01 \
    Assign03_02 \
    Assign03_03 \
    Assign03_04 \
    Assign03_05
########################################################################
Assign03_01: Assign03_01.class; $(JAVA) -cp $(MYCPS) Assign03_01
Assign03_01.class: Assign03_01.java; $(JAVAC) -cp $(MYCPS) Assign03_01.java

Assign03_02: Assign03_02.class; $(JAVA) -cp $(MYCPS) Assign03_02
Assign03_02.class: Assign03_02.java; $(JAVAC) -cp $(MYCPS) Assign03_02.java

Assign03_03: Assign03_03.class; $(JAVA) -cp $(MYCPS) Assign03_03
Assign03_03.class: Assign03_03.java; $(JAVAC) -cp $(MYCPS) Assign03_03.java

Assign03_04: Assign03_04.class; $(JAVA) -cp $(MYCPS) Assign03_04
Assign03_04.class: Assign03_04.java; $(JAVAC) -cp $(MYCPS) Assign03_04.java

Assign03_05: Assign03_05.class; $(JAVA) -cp $(MYCPS) Assign03_05
Assign03_05.class: Assign03_05.java; $(JAVAC) -cp $(MYCPS) Assign03_05.java
########################################################################
clean:: ; rm -f *~
########################################################################
cleanall:: clean
cleanall:: ; rm -f *.class
########################################################################
########################################################################
########################################################################
