import stack.DecodeString;

public class Main {
    public static void main(String[] args) {
        DecodeString obj = new DecodeString();
        System.out.println(obj.decodeString("2[abc]3[cd]ef"));
        StringBuilder builder = new StringBuilder();
        builder.append('1');
        builder.delete(0, builder.length());
        System.out.println(builder);
    }
}

