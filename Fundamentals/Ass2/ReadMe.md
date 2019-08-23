# Steps To Run this Stack Smashing Buffer Overflow Attack

* sudo apt-get install gcc-multilib
* sudo sysctl -w kernel.randomize_va_space=0​
  * If this is not working,
    * sudo gedit /etc/sysctl.conf
    * append this, kernel.randomize_va_space=0​
    * Save and Exit
* gcc -z execstack -fno-stack-protector harsh.c -m32 -o harsh -g
* gcc -z execstack -fno-stack-protector testme.c -m32 -o testme -g
* gcc -z execstack -fno-stack-protector exploit.c -m32 -o exploit -g
* ./exploit

## Still there is a high chance, you won't be able to run this code, as you need to calculate ret address using gdb and change in exploit.c.