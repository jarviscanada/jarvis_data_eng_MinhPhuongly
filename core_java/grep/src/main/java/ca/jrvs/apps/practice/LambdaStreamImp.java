package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImp implements LambdaStreamExc{
    public static void main(String[] args) {
        IntStream abc = IntStream.range(1,7);

        LambdaStreamImp test_lambdaStream = new LambdaStreamImp();

        System.out.println(Arrays.toString(test_lambdaStream.getOdd(abc).boxed().toArray()));
    }
    @Override
    public Stream<String> createStrStream(String... strings) {
        return Stream.of(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(string->string.toUpperCase());
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern);
        return stringStream.filter(regexPattern.asPredicate()); //.asPredicate will call Matcher.find internally
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start,end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(item->Math.round(Math.sqrt(item)*100.0)/100.0);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(num -> num%2!=0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return null;
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {

    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {

    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        Stream<Integer> result = ints.flatMap(Collection::stream);
        return result.map(num -> num*num);
    }
}
