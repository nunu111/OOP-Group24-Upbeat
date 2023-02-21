import java.util.NoSuchElementException;

public class Tokenizer {
    private String src, next;
    private int pos;
    public Tokenizer(String _src) throws SyntaxError{
        this.src = _src;
        pos = 0;
        computeNext();
    }

    public boolean hasNextToken() {
        return next != null;
    }
    public String peek() {
        if (!hasNextToken()) throw new NoSuchElementException("no more tokens");
        return next;
    }

    public String consume() throws SyntaxError {
        if (!hasNextToken()) throw new NoSuchElementException("no more tokens");
        String result = next;
        computeNext();
        return result;
    }

    public boolean peek(String s){
        if(!hasNextToken())return false;
        return peek().equals(s);
    }

    public void consume(String s) throws SyntaxError {
        if(peek(s)) consume();
        else throw new SyntaxError( "expected: "+s);
    }

    private void computeNext() throws SyntaxError {
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && Character.isSpaceChar(src.charAt(pos))) {
            pos++;
        }
        if(pos == src.length()){
            next = null;
            return;
        }
        char c = src.charAt(pos);
        if(Character.isDigit(c)){
            s.append(c);
            for(pos++;pos <src.length() && (Character.isDigit(src.charAt(pos)) || src.charAt(pos) =='.') ;pos++) s.append(src.charAt(pos));
        }else if(Character.isAlphabetic(c) || c=='_' || c=='$'){
            s.append(c);
            for(pos++;pos <src.length();pos++){
                if(Character.isAlphabetic(src.charAt(pos)) || Character.isDigit(src.charAt(pos)) || src.charAt(pos)=='_' || src.charAt(pos)=='$') s.append(src.charAt(pos));
                else break;
            }
        }
        else if (c == '#') { //ignore comment
            for(pos++;pos <src.length() ;pos++) {
                if(src.charAt(pos)== '\n') {
                    pos++;
                    computeNext();
                    return;
                }
            }
        }
        else if(c=='+' ||c=='-'||c=='*'||c=='/'||c=='%'|| c =='(' ||c == ')'|| c =='^' || c =='=' || c =='{' || c =='}'){
            s.append(c);
            pos++;
        }
        else if (c == '\n'){
            pos++;
            computeNext();
            return;
        }
        else throw new SyntaxError("unknown character: " + c);
        next = s.toString();
    }
}
