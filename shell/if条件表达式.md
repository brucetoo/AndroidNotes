IF语句中的条件表达式
- 文件表达式
  - `if [ -f file ]` 如果文件存在
  - `if [ -d ... ]`  如果目录存在
  - `if [ -s file ]` 如果文件存在且非空
  - `if [ -r file ]` 如果文件存在且可读
  - `if [ -w file ]` 如果文件存在且可写
  - `if [ -x file ]` 如果文件存在且可执行
- 整数变量表达式
  - `if [  int1 -eq int2 ]` =
  - `if [  int1 -ne int2 ]` !=
  - `if [  int1 -ge int2 ]` >=
  - `if [  int1 -gt int2 ]` >
  - `if [  int1 -le int2 ]` <=
  - `if [  int1 -lt int2 ]` <
  
- 字符串变量表达式
  - `if [ $a = $b ]` a=b
  - `if [ $string1 !=  $string2 ]` string1!=string2
  - `if [ -n $string ]` or `if [ $string ]` string 非空
  - `if [ -z $string ]` string 为空
  
>注意: -eq  -ne  -lt  -nt只能用于整数，不适用于字符串，字符串等于用赋值号=  
  
- 逻辑表达式
  - `if [ ! 表达式 ]` 逻辑非
  - `if [ 表达式1 -a 表达式2 ]` 逻辑与
  - `if [ 表达式1 -o 表达式2 ]` 逻辑或
  
- test 条件表达式 
  >  if test $num -eq 0      等价于   if [ $num –eq 0 ]
  >  并且test表达式没有 [ ]
  