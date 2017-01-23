if __name__ == "__main__":

    filename = raw_input("Please enter the name of the datafile:")

    #key1 = raw_input("Please enter key 1:")
    #key2 = raw_input("Please enter key 2:")
    #key3 = raw_input("Please enter key 3:")

    key1 = "1311148 11"
    key2 = "142 47223 "
    key3 = "2183962 66"
    key4 = "2184067 67"
    key5 = "2979343 16"
    key6 = "3777690 77"
    key7 = "3777806 11"
    key8 = "5216563 91"
    key9 = "5216659 31"
    key10 = "5891943 66"
    key11 = "6618305 12"
    key12 = "6618402 13"
    key13 = "7290685 13"
    key14 = "7290794 84"
    key15 = "8002103 12"

    reducer0 = 0
    reducer1 = 0
    reducer2 = 0
    reducer3 = 0
    reducer4 = 0
    reducer5 = 0
    reducer6 = 0
    reducer7 = 0
    reducer8 = 0
    reducer9 = 0
    reducer10 = 0
    reducer11 = 0
    reducer12 = 0
    reducer13 = 0
    reducer14 = 0
    reducer15 = 0

    with open(filename,'r') as f:
        for line in f:
            recordk = line[0:9]
            if recordk <= key1:
                reducer0 += 1
            elif key1 < recordk <= key2:
                reducer1 += 1
            elif key2 < recordk <= key3:
                reducer2 += 1
            elif key3 < recordk <= key4:
                reducer3 += 1
            elif key4 < recordk <= key5:
                reducer4 += 1
            elif key5 < recordk <= key6:
                reducer5 += 1
            elif key6 < recordk <= key7:
                reducer6 += 1
            elif key7 < recordk <= key8:
                reducer7 += 1
	    elif key8 < recordk <= key9:
                reducer8 += 1
            elif key9 < recordk <= key10:
                reducer9 += 1
            elif key10 < recordk <= key11:
                reducer10 += 1
            elif key11 < recordk <= key12:
                reducer11 += 1
            elif key12 < recordk <= key13:
                reducer12 += 1
            elif key13 < recordk <= key14:
                reducer13 += 1
            elif key14 < recordk <= key15:
                reducer14 += 1
	    elif recordk > key15:
                reducer15 += 1

    target = open('count.txt', 'w')
    target.write(filename + '\n' + "reducer0:" + str(reducer0) +
                 '\n' + "reducer1:" + str(reducer1) +
                 '\n' + "reducer2:" + str(reducer2) +
                 '\n' + "reducer3:" + str(reducer3) +
                 '\n' + "reducer4:" + str(reducer4) +
                 '\n' + "reducer5:" + str(reducer5) +
                 '\n' + "reducer6:" + str(reducer6) +
                 '\n' + "reducer7:" + str(reducer7) +
                 '\n' + "reducer8:" + str(reducer8) +
                 '\n' + "reducer9:" + str(reducer9) +
                 '\n' + "reducer10:" + str(reducer10) +
                 '\n' + "reducer11:" + str(reducer11) +
                 '\n' + "reducer12:" + str(reducer12) +
                 '\n' + "reducer13:" + str(reducer13) +
                 '\n' + "reducer14:" + str(reducer14) +
                 '\n' + "reducer15:" + str(reducer15))
    sum = reducer0+reducer1+reducer2+reducer3+reducer4+reducer5+reducer6+reducer7+reducer8+reducer9+reducer10+reducer11+reducer12+reducer13+reducer14+reducer15

    print("["+str(reducer0)+","+str(reducer1)+","+str(reducer2)+","+str(reducer3)+","
+str(reducer4)+","+str(reducer5)+","+str(reducer6)+","+str(reducer7)+","
+str(reducer8)+","+str(reducer9)+","+str(reducer10)+","+str(reducer11)+","
+str(reducer12)+","+str(reducer13)+","+str(reducer14)+","+str(reducer15)+"]")

    print("["+str(sum-reducer0)+","+str(sum-reducer1)+","+str(sum-reducer2)+","+str(sum-reducer3)+","
+str(sum-reducer4)+","+str(sum-reducer5)+","+str(sum-reducer6)+","+str(sum-reducer7)+","
+str(sum-reducer8)+","+str(sum-reducer9)+","+str(sum-reducer10)+","+str(sum-reducer11)+","
+str(sum-reducer12)+","+str(sum-reducer13)+","+str(sum-reducer14)+","+str(sum-reducer15)+"]")
