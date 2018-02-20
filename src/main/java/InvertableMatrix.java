public class InvertableMatrix extends Matrix implements IInvertableMatrix{


    public InvertableMatrix(int n) {
        super(n);
    }

    public InvertableMatrix(InvertableMatrix other) {
        super(other);
    }


    public IInvertableMatrix getInverse() throws OutOfRangeException {
        int i,j,k;
        int size = getN();
        InvertableMatrix E = new InvertableMatrix(size);//единичная матрица
        InvertableMatrix A = new InvertableMatrix(this);
//заполнение единичной матрицы
        for (i = 0;i < E.getN();i++){
            for (j = 0;j < E.getN();j++){
                if (i == j) E.set(i,j,1);
                else E.set(i,j,0);
            }
        }
//Задаём номер ведущей строки (сначала 0,1...size)
        for (k = 0;k < size;k++){
            for (j = k + 1;j < size;j++){
                A.set(k,j,A.get(k,j) / A.get(k,k));//все элементы k-ой строки матрицы A, кроме k-ого и до него, делим на разрешающий элемент - a[k][k]
            }
            for (j = 0;j < size;j++){
                E.set(k,j,E.get(k,j) / A.get(k,k));//все элементы k-ой строки матрицы e, делим на разрешающий элемент - a[k][k]
            }
            A.set(k,k,A.get(k,k) / A.get(k,k));//элемент соответствующий  разрещающему - делим на самого себя(т.е получит. 1 )
//идём сверху вниз, обходя k-ую строку
            if (k > 0) {//если номер ведущей строки не первый
                for (i = 0;i < k;i++){   //строки, находящиеся выше k-ой
                    for (j = 0;j < size;j++){
                        E.set(i,j,E.get(i,j) - E.get(k,j) * A.get(i,k));//Вычисляем элементы матрицы e,идя по столбцам с 0 -ого  к последнему
                    }
                    for (j = size - 1;j >= k;j--){
                        A.set(i,j,A.get(i,j) - A.get(k,j) * A.get(i,k));//Вычисляем элементы матрицы a,идя по столбцам с последнего к k-ому
                    }
                }
            }
            for (i = k + 1;i < size;i++){   //строки, находящиеся ниже k-ой
                for (j = 0;j < size;j++){
                    E.set(i,j,E.get(i,j) - E.get(k,j) * A.get(i,k));
                }
                for (j=size-1;j>=k;j--){
                    A.set(i,j,A.get(i,j) - A.get(k,j) * A.get(i,k));
                }
            }
        }
        return E;//На месте исходной матрицы должна получиться единичная а на месте единичной - обратная.
    }

}
