PGDMP     +                    {         
   shopVeresk    15.2    15.2 F    L           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            M           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            N           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            O           1262    17475 
   shopVeresk    DATABASE     �   CREATE DATABASE "shopVeresk" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "shopVeresk";
                postgres    false            �            1259    17555 	   cart_rows    TABLE     �   CREATE TABLE public.cart_rows (
    id integer NOT NULL,
    amount numeric(38,2),
    count integer,
    cart_id integer,
    product_id integer
);
    DROP TABLE public.cart_rows;
       public         heap    postgres    false            �            1259    17554    cart_rows_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cart_rows_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.cart_rows_id_seq;
       public          postgres    false    227            P           0    0    cart_rows_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.cart_rows_id_seq OWNED BY public.cart_rows.id;
          public          postgres    false    226            �            1259    17477 
   categories    TABLE     ]   CREATE TABLE public.categories (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.categories;
       public         heap    postgres    false            �            1259    17476    categories_id_seq    SEQUENCE     �   CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.categories_id_seq;
       public          postgres    false    215            Q           0    0    categories_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;
          public          postgres    false    214            �            1259    17484    image    TABLE     ~   CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);
    DROP TABLE public.image;
       public         heap    postgres    false            �            1259    17483    image_id_seq    SEQUENCE     �   CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    217            R           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    216            �            1259    17562 
   order_rows    TABLE     �   CREATE TABLE public.order_rows (
    id integer NOT NULL,
    amount numeric(38,2),
    count integer,
    order_id integer,
    product_id integer
);
    DROP TABLE public.order_rows;
       public         heap    postgres    false            �            1259    17561    order_rows_id_seq    SEQUENCE     �   CREATE SEQUENCE public.order_rows_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.order_rows_id_seq;
       public          postgres    false    229            S           0    0    order_rows_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.order_rows_id_seq OWNED BY public.order_rows.id;
          public          postgres    false    228            �            1259    17538    orders    TABLE     .  CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp(6) without time zone,
    number character varying(255),
    price real NOT NULL,
    status smallint,
    person_id integer NOT NULL,
    product_id integer NOT NULL,
    amount numeric(38,2)
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    17537    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    225            T           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    224            �            1259    17491    persons    TABLE     �   CREATE TABLE public.persons (
    id integer NOT NULL,
    login text NOT NULL,
    password character varying(255),
    role character varying(255)
);
    DROP TABLE public.persons;
       public         heap    postgres    false            �            1259    17490    persons_id_seq    SEQUENCE     �   CREATE SEQUENCE public.persons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.persons_id_seq;
       public          postgres    false    219            U           0    0    persons_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.persons_id_seq OWNED BY public.persons.id;
          public          postgres    false    218            �            1259    17500    product    TABLE     �  CREATE TABLE public.product (
    id integer NOT NULL,
    exp_date text NOT NULL,
    action_date text NOT NULL,
    date_time timestamp(6) without time zone,
    description text NOT NULL,
    title text NOT NULL,
    price real NOT NULL,
    weight double precision NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT products_price_check CHECK ((price >= (1)::double precision)),
    CONSTRAINT products_weight_check CHECK ((weight >= (1)::double precision))
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    17521    product_cart    TABLE     m   CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);
     DROP TABLE public.product_cart;
       public         heap    postgres    false            �            1259    17520    product_cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.product_cart_id_seq;
       public          postgres    false    223            V           0    0    product_cart_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.product_cart_id_seq OWNED BY public.product_cart.id;
          public          postgres    false    222            �            1259    17499    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    221            W           0    0    products_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.products_id_seq OWNED BY public.product.id;
          public          postgres    false    220            �           2604    17558    cart_rows id    DEFAULT     l   ALTER TABLE ONLY public.cart_rows ALTER COLUMN id SET DEFAULT nextval('public.cart_rows_id_seq'::regclass);
 ;   ALTER TABLE public.cart_rows ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226    227            �           2604    17480    categories id    DEFAULT     n   ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);
 <   ALTER TABLE public.categories ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    17487    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    17565    order_rows id    DEFAULT     n   ALTER TABLE ONLY public.order_rows ALTER COLUMN id SET DEFAULT nextval('public.order_rows_id_seq'::regclass);
 <   ALTER TABLE public.order_rows ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    228    229            �           2604    17541 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    225    225            �           2604    17494 
   persons id    DEFAULT     h   ALTER TABLE ONLY public.persons ALTER COLUMN id SET DEFAULT nextval('public.persons_id_seq'::regclass);
 9   ALTER TABLE public.persons ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    17503 
   product id    DEFAULT     i   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    17524    product_cart id    DEFAULT     r   ALTER TABLE ONLY public.product_cart ALTER COLUMN id SET DEFAULT nextval('public.product_cart_id_seq'::regclass);
 >   ALTER TABLE public.product_cart ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            G          0    17555 	   cart_rows 
   TABLE DATA           K   COPY public.cart_rows (id, amount, count, cart_id, product_id) FROM stdin;
    public          postgres    false    227   -P       ;          0    17477 
   categories 
   TABLE DATA           .   COPY public.categories (id, name) FROM stdin;
    public          postgres    false    215   JP       =          0    17484    image 
   TABLE DATA           :   COPY public.image (id, file_name, product_id) FROM stdin;
    public          postgres    false    217   �P       I          0    17562 
   order_rows 
   TABLE DATA           M   COPY public.order_rows (id, amount, count, order_id, product_id) FROM stdin;
    public          postgres    false    229   �U       E          0    17538    orders 
   TABLE DATA           l   COPY public.orders (id, count, date_time, number, price, status, person_id, product_id, amount) FROM stdin;
    public          postgres    false    225   �U       ?          0    17491    persons 
   TABLE DATA           <   COPY public.persons (id, login, password, role) FROM stdin;
    public          postgres    false    219   FV       A          0    17500    product 
   TABLE DATA           w   COPY public.product (id, exp_date, action_date, date_time, description, title, price, weight, category_id) FROM stdin;
    public          postgres    false    221   �V       C          0    17521    product_cart 
   TABLE DATA           A   COPY public.product_cart (id, person_id, product_id) FROM stdin;
    public          postgres    false    223   #_       X           0    0    cart_rows_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.cart_rows_id_seq', 1, false);
          public          postgres    false    226            Y           0    0    categories_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.categories_id_seq', 1, false);
          public          postgres    false    214            Z           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 80, true);
          public          postgres    false    216            [           0    0    order_rows_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.order_rows_id_seq', 1, false);
          public          postgres    false    228            \           0    0    orders_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.orders_id_seq', 8, true);
          public          postgres    false    224            ]           0    0    persons_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.persons_id_seq', 10, true);
          public          postgres    false    218            ^           0    0    product_cart_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.product_cart_id_seq', 13, true);
          public          postgres    false    222            _           0    0    products_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.products_id_seq', 16, true);
          public          postgres    false    220            �           2606    17560    cart_rows cart_rows_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cart_rows
    ADD CONSTRAINT cart_rows_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.cart_rows DROP CONSTRAINT cart_rows_pkey;
       public            postgres    false    227            �           2606    17482    categories categories_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_pkey;
       public            postgres    false    215            �           2606    17489    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    217            �           2606    17567    order_rows order_rows_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.order_rows
    ADD CONSTRAINT order_rows_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.order_rows DROP CONSTRAINT order_rows_pkey;
       public            postgres    false    229            �           2606    17543    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    225            �           2606    17498    persons persons_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.persons DROP CONSTRAINT persons_pkey;
       public            postgres    false    219            �           2606    17526    product_cart product_cart_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT product_cart_pkey;
       public            postgres    false    223            �           2606    17509    product products_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.product
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.product DROP CONSTRAINT products_pkey;
       public            postgres    false    221            �           2606    17532 '   product_cart fk3u44w5jymxki9ux5cgemwc0a    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fk3u44w5jymxki9ux5cgemwc0a FOREIGN KEY (product_id) REFERENCES public.product(id);
 Q   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT fk3u44w5jymxki9ux5cgemwc0a;
       public          postgres    false    3225    221    223            �           2606    17544 "   orders fk9qntjmy82o15pyak9igalicsn    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk9qntjmy82o15pyak9igalicsn FOREIGN KEY (person_id) REFERENCES public.persons(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk9qntjmy82o15pyak9igalicsn;
       public          postgres    false    219    3223    225            �           2606    17578 &   order_rows fkbl4fefb8g6q35dwsfg5t2hbes    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_rows
    ADD CONSTRAINT fkbl4fefb8g6q35dwsfg5t2hbes FOREIGN KEY (order_id) REFERENCES public.orders(id);
 P   ALTER TABLE ONLY public.order_rows DROP CONSTRAINT fkbl4fefb8g6q35dwsfg5t2hbes;
       public          postgres    false    229    225    3229            �           2606    17568 %   cart_rows fkcjxall1m754vkylhnolb225am    FK CONSTRAINT     �   ALTER TABLE ONLY public.cart_rows
    ADD CONSTRAINT fkcjxall1m754vkylhnolb225am FOREIGN KEY (cart_id) REFERENCES public.product_cart(id);
 O   ALTER TABLE ONLY public.cart_rows DROP CONSTRAINT fkcjxall1m754vkylhnolb225am;
       public          postgres    false    3227    223    227            �           2606    17573 %   cart_rows fkcqfybhd228kuu1c7do3gup6cj    FK CONSTRAINT     �   ALTER TABLE ONLY public.cart_rows
    ADD CONSTRAINT fkcqfybhd228kuu1c7do3gup6cj FOREIGN KEY (product_id) REFERENCES public.product(id);
 O   ALTER TABLE ONLY public.cart_rows DROP CONSTRAINT fkcqfybhd228kuu1c7do3gup6cj;
       public          postgres    false    221    227    3225            �           2606    17527 (   product_cart fkexa266xt8qv9akkd127b7q9q7    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkexa266xt8qv9akkd127b7q9q7 FOREIGN KEY (person_id) REFERENCES public.persons(id);
 R   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT fkexa266xt8qv9akkd127b7q9q7;
       public          postgres    false    3223    219    223            �           2606    17583 &   order_rows fkjate452iyi7x5j68dtqhuxbkl    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_rows
    ADD CONSTRAINT fkjate452iyi7x5j68dtqhuxbkl FOREIGN KEY (product_id) REFERENCES public.product(id);
 P   ALTER TABLE ONLY public.order_rows DROP CONSTRAINT fkjate452iyi7x5j68dtqhuxbkl;
       public          postgres    false    221    229    3225            �           2606    17549 "   orders fkkp5k52qtiygd8jkag4hayd0qg    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkkp5k52qtiygd8jkag4hayd0qg FOREIGN KEY (product_id) REFERENCES public.product(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fkkp5k52qtiygd8jkag4hayd0qg;
       public          postgres    false    221    225    3225            �           2606    17510 !   image fko6o5cjl2h3n5g87df5afdt17f    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT fko6o5cjl2h3n5g87df5afdt17f FOREIGN KEY (product_id) REFERENCES public.product(id);
 K   ALTER TABLE ONLY public.image DROP CONSTRAINT fko6o5cjl2h3n5g87df5afdt17f;
       public          postgres    false    221    3225    217            �           2606    17515 #   product fkog2rp4qthbtt2lfyhfo32lsw9    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);
 M   ALTER TABLE ONLY public.product DROP CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9;
       public          postgres    false    221    3219    215            G      x������ � �      ;   l   x����PD��V�*0�`7��@B(A7n�"�@g:򲚓9��>,v�U8&u�,Q�*,�*Y��T���T��uM:{_$�^�����E����j,���B�C�      =   �  x�-UK�-5笂#ŉ��
� C&�����.�'$X=���Z}�J�.W�[�h��1��&���rRcI�����|~����7�����?N��S��$�&�qH�>4�/�y�X����r$ɳ��A>����h�%���}����B�T�漈ui"d�+�h���}_H�i�BܹhI��VZǌ�VoΧ�87���Z��P������J1�Q�5��K�?��/���w��W�����??�^�K2a5E�b�'-4"��S�V�Q��/hM�^�n&_=H�7Z[���W��z�������p����Ug�]8 g��@5�>mo:~��Ps�[�����m���ˊ��$R���C�Y�T����?��nO^��^��C�m��u��M�p�.�m�w@GZgT�9�W� ��Lc�v�k��:S��y.��26��u	�jE�-9Y�\K�T�`D���c̭���*�m��F^A�8;����{T������1���놰��|�	9Y�qa}�ؚΩE�L�,�dkG-k���Q�>�`%a�8
�M�����]h��{����n�[&���0�2/8g:�~��]5����z�9T��u>�5�o�����/��Z^�������|a�K��L�u�v$�#d/�~L(O#3�1O;3G����T�p���s�oG@3
Ɗ��rҬ-l,LƮV�T����W��`Ϛa�S���q
t�8�ͯV?~�]����j%A�E�=��ӌf|�l��z�,�j�J)��0>�be�<�Zk=XL���&�+0�{�0a~�w<XMU�Wk�pÕqSخ��k�����Z�xCD��lR+g`\����}R���zھ�g�U����BB�K^�2܅i�F�/B$C����D�;�����ir��n��ʈ%��}���Ab���M�+<`�,��K��R�g����r�l()[�t�D6����1����׿���ߺ�W/���"	�G`rcOB �J�z�ؚ6Ҳ.���.�ؘ�[�Q\�n>V�Zʄ�rc�ab�Lh��� ������{�؄�aA"�k�\ؖ�D�����`�-��X�Ҽ��t)O����M��i�i���r�j��g��K������-��U�6zq'�־�G<ؙ��`�m*��K��q)`p�2�a���)c���j`��O�+�A&��s3D��؟ޯ��^�o      I      x������ � �      E   �   x��α1F�ڙ�|�o;q�!��|��? *��ݫ>=H�(gc�m(F�3�B���g='[p�Q�b&����LJBF�5�7�g���_��?Z~y�����2`����c������jʡ�2V�
���}K)�2RCK      ?   �   x�=��
�0  �g�>k�M�Qͬ�L6�A,��2�T�|}A��D}k:����L'����1��h�Ui1�p��UFH��&:}H�y�H�.����$<x�t�K���	�ZS�ܘ��=٘;I�>)»���-�鞌����Z}�_�B
� ��04      A     x��X[n�V��Vq���Q�lً�
��~t�����h�� @8(�_�h�Em��Q眙KQ'HТ( ���{��̙93�0
:A����f;jv�.ϻ��N�ꆽ��yV�a>Χ�.�O�ۺY\˟���Wy��.OeA~��b$�SY���y�p�@�e�e�����"�euU�cO���r*�����|�a,�"d\>/�D~R�.F?_�$Zn���1�p�݉�+��]��⺕�Z��b��aŰ�u�v����7Ž���,�Mb���A@t*�^��˩�-���[!GV��Z&"dA�߻��{'\�5���΄��
K�Lml��ħ��������X��<��{�{&�@�ڋ#�0!
c�����^ �(�w����d�'	�WX��\�D3�L��a��Ѩ��wN/PT�)J��'�VY(���+� �lŝ�����_�?)L�mOt4pN��`%<L0��"�ڒGh$�m۔�Ǌ��D?�W�j�
~�� �J�鈿!�]��'�X�3R�	����kJ�����+�u�E�2|������h,�T��	��-�/�'��{^���盛���6�۰�D�vv�A��	"�Cp�c����u�b�<��c����<X�)8�50��*ې�+"�Z���V��I5\��]�s�D)��KАTb�,&R"@���)ݮ��&k��3,$ �(�4mco���^�0�v&})-Prp� 1*$�["��~J�n�CyLD�;�%�Ft �5�<kf*�)Gg�6�W�A�5�f�)�H[�7:�Rs�"���F�,�f����Cr��]O�r�F�5��fı��l�MqR\B������ӕ$l�g�V��C&hւu7�1���<��\~%�|0_�q� lK~t�ӫEAO+(�㣅Ȃ���tV�,n(�P7Y�D>uZ*�%Ɇn�bz�v�9�F����|G��.��4��[$F�d��^r��w@�C�My����0�D�58Y���Yj��=0�E��/y�ִ;1}Y�|1~d���ZJfD�$Ʋ���5di�����\���[К����/���������yt�:k���QP����N�~��upo�B���Qۼ�S6�c1\��m�4��Na�_�u[��qa�4k��J���W� ���+n[{�A�c]BޠN���5���՟s��m#d0l��МQ�>���Dic��rZ�~-}U��4MP�cIˎ@�OTO�p��yt�꟞u�aU�g\�fE�N�#-�� ��ϱ#�ݺ��u6D|��E\�O|�g4h�v�.�ؑ�Cо5��po�b���S&��m���8G�g�Ėa�`Gb��o�zlYܵ�HW��
Q�P�������ck~ig�S��|��E�&�k��&-��g6$�C��x�|�o��G���2ڨ$;CJ�7��
f֥me����Z>3�?��~�}��EόA:E� 	Z� Y9��Ɂ0N�{A�/�d�'K�ܡ�+g.��HX�$C�S�
�뵩1���Ê����g|��i�qŰM���k`m��6kt�ڛ��"�}*�Le;��S��o�*����"c"��O*֣��5}[� ��
"~�Fx�#�b�;Ĭ�Q_A���������5��G,�sK.�bWB
����y�CyqR^[o���E�v���b�=�[�i�b���7�^��&������@T�{���Yלj\�{�m[G�{�m��֨T��N^��d�����3���޵6[�6#u��>tl����5��3`�l�,^h�V{2U%��c��\`G|Hr��l�?��3GS�����6�>i,��B}"��ǭJ��U���/�T}�V�?��SބA�����V)�ȋ�ݗtK�cm1�}�ʆ9�k���^r���Z���X�[�I�T^^���m{]f��a�|��G.��m/d��t�翑mR�9��n3���3]D$�D�,l�������J
�49�h�g����^��3���Us��r�w��?ڻ��x�e�wF5B���F�A�1�D���V0<6:�\��!�X�_�j��߷,?�      C      x������ � �     