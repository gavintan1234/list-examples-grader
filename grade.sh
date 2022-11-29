CP=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"
rm -rf student-submission
git clone $1 student-submission
echo 'Finished cloning'
cp TestListExamples.java student-submission/
cd student-submission/

if [[ !(-e ListExamples.java) ]]; then
    echo "ListExamples.java not found!"
    exit 1
fi

javac -cp $CP ListExamples.java
if [[ $? -ne 0 ]]; then
    echo "ListExamples.java failed to compile!"
    exit 1
fi

javac -cp $CP TestListExamples.java
if [[ $? -ne 0 ]]; then
    echo "TestListExamples.java failed to compile!"
    exit 1
fi

java -cp $CP org.junit.runner.JUnitCore TestListExamples|head -n 2|tail -n 1 > TestOutput.txt

NUMTESTS=$(grep -o '.' TestOutput.txt|wc -l)
NUMFAILED=$(grep -o 'E' TestOutput.txt|wc -l)
NUMPASSED=$(($NUMTESTS - $NUMFAILED))

echo "Number of tests passed: $NUMPASSED"
echo "Number of tests failed: $NUMFAILED"
echo "Final score: $NUMPASSED/$NUMTESTS"