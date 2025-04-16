import java.util.Arrays;

class Solution {
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }

            return Integer.compare(o1.length(), o2.length());
        });

        for (String ban : bans) {
            if (convertSpellToSequence(ban) < n + 1) {
                n++;
            }
        }

        return convertSequenceToSpell(n);
    }

    public long convertSpellToSequence(String spell) {
        long sequence = 0L;

        for (int index = 0; index < spell.length(); index++) {
            sequence += (long) (spell.charAt(index) - 'a' + 1) * (long) Math.pow(26, spell.length() - index - 1);
        }

        return sequence;
    }

    public String convertSequenceToSpell(long sequence) {
        String spell = "";

        for (int index = 0; index <= 10; index++) {
            for (long alphabet = 26; alphabet >= 1; alphabet--) {
                long offset = alphabet * (long) Math.pow(26, 10 - index);

                if (sequence >= offset && sequence - offset >= (long) Math.pow(26, 9 - index)) {
                    sequence -= offset;
                    spell += (char) (alphabet + 'a' - 1);
                    break;
                }
            }
        }

        return spell;
    }
}