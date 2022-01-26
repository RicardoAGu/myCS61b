public class Hello_NumbersJava {
    public static void main(String[] args){
        int x = 0;
        while (x < 10) {
            System.out.println(x);
            x += 1;
        }
    }
}


/*
1. 使用变量之前必须声明
2. 变量不能被赋予一个与类型不符的值，且类型也不能被改变
3. 在代码运行前验证类型，如果有一个地方的类型出错，前面的程序都不会被运行（逻辑错误不知道能不能运行之前的程序）
 */