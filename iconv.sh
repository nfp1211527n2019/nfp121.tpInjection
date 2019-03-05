#! /bin/bash
to="utf-8"
x=$(file -i $1)
from=${x#*charset=}
# echo "FROM $1 is $from"
if [[ $from != $to ]]; then
    echo "iconv -f $from -t $to $1 -o $1"
	iconv -f $from -t $to $1 -o $1
fi
