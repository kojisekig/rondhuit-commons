#!/bin/bash
#
# Copyright (c) 2014 RONDHUIT Co.,Ltd.
#

if [ ! -e api ]; then
  echo "You need to build javadoc via ant commons-javadoc before executing me."
  exit 1
fi

WORK=javadoc-release

rm -Rf $WORK
git clone --branch gh-pages https://github.com/kojisekig/rondhuit-commons.git $WORK; cd $WORK

rm -Rf api
cp -R ../api .
git add api
git commit -m "publish new javadoc"
git push

cd ..;rm -Rf $WORK
