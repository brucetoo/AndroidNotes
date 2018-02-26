#! /bin/sh
echo "hello shell!"

#Shell的变量只有字符串类型，定义变量=两边不能有空格，当变量之间有空格时，必须加上双引号(单引号)
name="this is my name!"

# {}的作用是定义变量的边界，可有可无
echo ${name}

#被双引号括起来的变量会发生变量替换, 单引号不会
str='abc'
echo "双引号 printt ${str}"
echo '单引号 printt ${str}'

#计算计算字符串长度
strLength="abcdef"
echo "string length is: ${#strLength}"

#简单截取字符串
strCut="hello shell!"
echo ${strCut:2} #截取到第二位
echo ${strCut:1:3} #截取1-3位之间字符串

#if/else语句
#条件与括号之间必须要有空格，否则会报错
a=1
if [ "1"=$a ]; then
	echo "you input 1"
elif [[ $1=2 ]]; then
	echo "you input 2"
fi

#switch语句
input="1"
switchStr="dd"
case $input in
	1 | 0) #1.每个条件后必须加)
	switchStr="一或者零";; #2.每天语句执行完必须加;; 代表 break
    2)
    switchStr="二";;
    3)
    switchStr="三";;
    *)
    switchStr=$input;;
esac # 3.语句执行完成需加反case -> esac
echo "switch语句 print:$switchStr"

#for循环
# for name [in list]
# do
# 	...
# done
#[]括起来的 in list, 为可选部分, 如果省略in list则默认为in "$@", 即你执行此命令时传入的参数列表
for file in *.* #打印当前目录所有的文件名字
do
	echo $file
done

#while循环
# while condition
# do
# 	do something...
# done
index=0
while [[ index -le 5 ]]; do
	echo "index=$index"
	let index++
done

#util循环
# until condition
# do
# 	do something...
# done

untilIndex=5
until [[ untilIndex -eq 0 ]]; do
	echo "untilIndex=$untilIndex"
	let untilIndex--
done

# $?在shell中保存的是上一条命令的返回值

#脚本传递参数
echo "$# parameters"; #1.$#代表所有参数的个数
echo "$@"; #2.$@代表所有参数的内容
echo "$0" #3.$0代表脚本的名称
echo "$1" #4.$1代表第一个参数的值
