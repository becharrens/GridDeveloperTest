SOURCE_DIR	:= src
OUTPUT_DIR	:= bin

# Tools

FIND	:= find
RM	:= rm -rf
MKDIR	:= mkdir -p
JAVA	:= java
JAVAC	:= javac

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR)

# the make rules

all: rules

# runs the antlr build script then attempts to compile all .java files within src
rules:
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) rules

clean:
	$(RM) rules $(OUTPUT_DIR)

.PHONY: all rules clean


