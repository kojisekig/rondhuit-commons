# HOW TO RELEASE

## RELEASE 0.1.0

### make a branch for 0.1
git checkout -b rel-0.1

### set the proper version number and date
> change in the following files 0.1-dev to 0.1.0
> and YYYY-MM-DD to today

vi version.properties

vi CHANGES.txt

### commit the modification
git add .

git commit -m "prepare rel-0.1.0"

git push --set-upstream origin rel-0.1

### tag the commit point to rel-0.1.0
git tag -a rel-0.1.0 -m "release tag for 0.1.0"

git push origin rel-0.1.0

### build the release JAR file
ant

### got to GitHub site and the
go to <https://github.com/kojisekig/rondhuit-commons/releases/tag/rel-0.1.0>

click "Edit tag" button

Drop down RONDHUIT-COMMONS-0.1.0.jar file to the drop down box and click "Publish release"

the download link will be <https://github.com/kojisekig/rondhuit-commons/releases/download/rel-0.1.0/RONDHUIT-COMMONS-0.1.0.jar>

### merge the change to master
git checkout master

git merge rel-0.1

### prepare the next release 0.2-dev on master
vi CHANGES.txt

vi version.properties

git add .

git commit -m "prepare the next release 0.2"

git push

### prepare the next bug fix release 0.1.1 on the branch rel-0.1
git checkout rel-0.1

vi CHANGES.txt

vi version.properties

git add .

git commit -m "prepare the next release 0.1.1"

git push
